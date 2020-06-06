package com.feng.book_store_demo.script.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feng.book_store_demo.entity.BookCategory;
import com.feng.book_store_demo.entity.BookInfo;
import com.feng.book_store_demo.entity.BookSpecs;
import com.feng.book_store_demo.repository.BookCategoryRepository;
import com.feng.book_store_demo.repository.BookInfoRepository;
import com.feng.book_store_demo.repository.BookSpecsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
@Transactional
@Component("bookTask")
@Slf4j
public class BookTask {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Autowired
    private BookCategoryRepository categoryRepository;
    @Autowired
    private BookSpecsRepository specsRepository;
    private int categoryTypeNum = 0;

    public void download() {
        String url = "https://list.jd.com/list.html?cat=1713%2C3287%2C3797&page=";
        for (int i = 1; i <= 1; i = i + 1) {
            String html = httpUtil.getHtml(url + i);
            try {
                parse(html);
                log.info("第{}次抓取完成", i);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        log.info("抓取完成");
    }

    private void parse(String html) throws JsonProcessingException {
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div#J_goodsList > ul >li");
        for (Element element : elements) {

            //跳过广告
            Element adv = element.select("div > span.p-promo-flag").first();
            if (adv!=null&&adv.text().contains("广告")){
               continue;
           }
            //获取书籍名称
            BookInfo bookInfo = new BookInfo();
            String bookName = element.select("div.gl-i-tab-content > div:nth-child(1) > div.p-name > a > em").text();
            if (bookName == null || bookName.isEmpty()) {
                bookName = element.select(" div > div.p-name > a > em").text();
            }
            bookInfo.setBookName(bookName);

            //获取商品类别
            String categoryName = CategoryUtil.getCategory(bookName);
            BookCategory bookCategory = new BookCategory();
            Date date = new Date();
            BookCategory category = categoryRepository.findBookCategoryByCategoryName(categoryName);
            if (category == null) {
                bookCategory.setCategoryName(categoryName);
                bookCategory.setCategoryType(++categoryTypeNum);
                bookCategory.setCreateTime(date);
                bookCategory.setUpdateTime(date);
                categoryRepository.save(bookCategory);
                bookInfo.setCategoryType(categoryTypeNum);
                bookInfo.setBookDescription(categoryName);
            } else {
                bookInfo.setCategoryType(category.getCategoryType());
            }

            //获取书籍价格
            String rowPrice = element.select("div.p-price > strong > i").text();
            double parseDouble = Double.parseDouble(rowPrice);
            Integer bookPrice = (int) (parseDouble * 100);
            bookInfo.setBookPrice(bookPrice);



            //获取图片
            Elements tmp = element.select("div.p-img  img");
            Element tmp1 = tmp.first();
            String imageUrl = "https:" + tmp1.attr("src");
            //当图片路径出现特殊字符时，无法解析会抛出异常，捕获此异常设置图片路径为空
            try {
                String imageName = httpUtil.getImage(imageUrl);
                bookInfo.setBookIcon(imageName);
            } catch (RuntimeException e) {
                bookInfo.setBookIcon("");
            }
            //获取书籍出版社
            Elements shopNumEle = element.select("div.p-shopnum");
            if (shopNumEle==null){
                String bookDesc = element.select("div.gl-i-tab-content > div.tab-content-item.tab-cnt-i-selected > div.p-shopnum > a").text();
                if (bookDesc.contains("京东自营"))
                    bookDesc="其他";
                bookInfo.setBookDescription(bookDesc);
            }
            if (shopNumEle!=null){
                String bookDesc = shopNumEle.first().text();
                if (bookDesc.contains("京东自营"))
                    bookDesc="其他";
                bookInfo.setBookDescription(bookDesc);
            }


            //保存商品标签
            Elements tagElements = element.select("div.p-bookdetails >span");
            if (tagElements != null) {
                StringBuilder tagString = new StringBuilder();
                for (Element tagElement : tagElements) {
                    tagString.append(tagElement.text());
                }
                bookInfo.setBookTag(tagString.toString());
            }
            //设置默认库存
            bookInfo.setBookStock(10);
            bookInfo.setCreateTime(date);
            bookInfo.setUpdateTime(date);
            BookInfo bookInfoRes = bookInfoRepository.save(bookInfo);

            //保存bookSpec
            Elements elementTabs = element.select("div.gl-i-tab-trigger");
            //如果存在多个规格
            if (elementTabs != null&&elementTabs.size()>0) {
                Elements spans = elementTabs.select("span");
                String[] specPreNames = new String[spans.size()];
                Iterator<Element> iterator = spans.iterator();
                for (int i = 0; iterator.hasNext(); i++) {
                    specPreNames[i] = iterator.next().text();
                }

                Elements elementTabItems = element.select("div.gl-i-tab-content > div");
                int i = 0;
                int basePrice=0;
                for (Element elementTabItem : elementTabItems) {
                    BookSpecs bookSpecs = new BookSpecs();
                    String sufName = elementTabItem.select("div.p-name > a > em").text();
                    bookSpecs.setSpecsName("【" + specPreNames[i] + "】" + sufName);
                    bookSpecs.setBookId(bookInfoRes.getBookId());
                    bookSpecs.setSpecsStock(5);
                    String rPrice = elementTabItem.select("div.p-price > strong > i").text();
                    //另一种价格需要点击后京东动态渲染，获取不到,默认为1.75倍
                    if (rPrice.isEmpty()){
                        basePrice= (int)Math.round((basePrice/100) * 1.75 * 100);
                        bookSpecs.setSpecsPrice(basePrice);
                    }
                    else {
                        Integer specsPrice = (int) (Double.parseDouble(rPrice) * 100);
                        basePrice=specsPrice;
                        bookSpecs.setSpecsPrice(specsPrice);
                    }

                    Element specImgElement = elementTabItem.select("div.p-img  img").first();
                    String specImageUrl = "https:" + specImgElement.attr("src");
                    //当图片路径出现特殊字符时，无法解析会抛出异常，捕获此异常设置图片路径为空
                    try {
                        String imageName = httpUtil.getImage(specImageUrl);
                        bookSpecs.setSpecsIcon(imageName);
                        bookSpecs.setSpecsPreview(imageName);
                    } catch (RuntimeException e) {
                        bookSpecs.setSpecsIcon("");
                        bookSpecs.setSpecsPreview("");
                    }
                    bookSpecs.setCreateTime(date);
                    bookSpecs.setUpdateTime(date);
                    specsRepository.save(bookSpecs);
                    i++;
                }
                bookInfoRes.setBookStock(5 * elementTabItems.size());//更新总库存使之等于各规格库存
                bookInfoRepository.save(bookInfoRes);
            } //如果只存在一个规格
            Elements select = element.select("div.gl-i-tab");
            if (element.select("div.gl-i-tab")==null||select.size()==0){
                BookSpecs bookSpecs = new BookSpecs();
                bookSpecs.setBookId(bookInfoRes.getBookId());
                bookSpecs.setSpecsName(bookInfo.getBookName());
                bookSpecs.setSpecsStock(bookInfo.getBookStock());
                bookSpecs.setSpecsPrice(bookInfo.getBookPrice());
                bookSpecs.setSpecsIcon(bookInfo.getBookIcon());
                bookSpecs.setSpecsPreview(bookInfo.getBookIcon());
                bookSpecs.setCreateTime(bookInfo.getCreateTime());
                bookSpecs.setUpdateTime(bookInfo.getUpdateTime());
                specsRepository.save(bookSpecs);
            }
        }
    }
}
