package com.feng.book_store_demo.service.impl;

import com.feng.book_store_demo.constant.enums.BookEnum;
import com.feng.book_store_demo.entity.BookCategory;
import com.feng.book_store_demo.entity.BookInfo;
import com.feng.book_store_demo.entity.BookSpecs;
import com.feng.book_store_demo.exception.BookException;
import com.feng.book_store_demo.repository.BookCategoryRepository;
import com.feng.book_store_demo.repository.BookInfoRepository;
import com.feng.book_store_demo.repository.BookSpecsRepository;
import com.feng.book_store_demo.service.BookService;
import com.feng.book_store_demo.util.StringFormat;
import com.feng.book_store_demo.util.TagsSplitUtil;
import com.feng.book_store_demo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author shf
 * @Date 2020/6/4 19:29
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookInfoRepository bookRepository;
    @Autowired
    private BookCategoryRepository categoryRepository;
    @Autowired
    private BookSpecsRepository specsRepository;

    @Override
    public BookIndexVO getBookIndexVO() {
        BookIndexVO bookIndexVO = new BookIndexVO();
        List<BookCategory> categories = categoryRepository.findAll();
        List<BookCategoryVO> categoryVOS = categories.stream()
                .map(t -> new BookCategoryVO(
                        t.getCategoryName(),
                        t.getCategoryId()
                )).collect(Collectors.toList());


        List<BookInfo> bookInfos = bookRepository.findBookInfoByCategoryType(categories.get(0).getCategoryId());
        List<BookInfoVO> bookInfoVOS = bookInfos.stream()
                .map(b -> new BookInfoVO(
                        b.getBookId(),
                        b.getBookName(),
                        StringFormat.formatDecimal(b.getBookPrice() / 100.0),
                        //String.valueOf(b.getBookPrice() / 100.0),
                        b.getBookDescription(),
                        TagsSplitUtil.split(b.getBookTag()),
                        "../images/" + b.getBookIcon()
                )).collect(Collectors.toList());
        bookIndexVO.setCategories(categoryVOS);
        bookIndexVO.setBooks(bookInfoVOS);
        return bookIndexVO;
    }

    public List<BookInfoVO> getBookByCategoryType(Integer id) {
        List<BookInfo> bookInfos = bookRepository.findBookInfoByCategoryType(id);
        return bookInfos.stream()
                .map(b -> new BookInfoVO(
                        b.getBookId(),
                        b.getBookName(),
                        StringFormat.formatDecimal(b.getBookPrice() / 100.0),
                        b.getBookDescription(),
                        TagsSplitUtil.split(b.getBookTag()),
                        "../images/" + b.getBookIcon()
                )).collect(Collectors.toList());
    }

    public BookSpecsVO getSpecsByBookId(Integer id) {
        List<BookSpecs> specsList = specsRepository.findBookSpecsByBookId(id);
        if (!bookRepository.findById(id).isPresent()) {
            log.error("【图书异常】图书不存在");
            throw new BookException(BookEnum.BOOK_NOT_EXIST);
        } else {
            BookInfo bookInfo = bookRepository.findById(id).get();
            BookSpecsVO bookSpecsVO = new BookSpecsVO();
            //sku
            SkuVO skuVO = new SkuVO();
            List<SkuTreeVO> skuTreeVOList = new ArrayList<>();
            List<SpecsDetailVO> detailVOList = new ArrayList<>();
            List<SpecsNumberVO> numberVOList = new ArrayList<>();
            SkuTreeVO skuTreeVO = new SkuTreeVO();
            for (BookSpecs bookSpecs : specsList) {
                SpecsDetailVO detailVO = new SpecsDetailVO();
                SpecsNumberVO numberVO = new SpecsNumberVO();

                detailVO.setId(bookSpecs.getSpecsId());
                detailVO.setName(bookSpecs.getSpecsName());
                detailVO.setImgUrl("../images/" + bookSpecs.getSpecsIcon());
                detailVO.setPreviewImgUrl("../images/" + bookSpecs.getSpecsPreview());

                numberVO.setS1(bookSpecs.getSpecsId());
                numberVO.setPrice(new BigDecimal(bookSpecs.getSpecsPrice()));
                numberVO.setStock_num(bookSpecs.getSpecsStock());

                numberVOList.add(numberVO);
                detailVOList.add(detailVO);
            }
            skuTreeVO.setV(detailVOList);
            skuTreeVOList.add(skuTreeVO);

            skuVO.setTree(skuTreeVOList);
            skuVO.setList(numberVOList);
            skuVO.setPrice(StringFormat.formatDecimal(bookInfo.getBookPrice() / 100.0));
            skuVO.setStock_num(bookInfo.getBookStock());
            skuVO.setHide_stock(false);
            skuVO.setNone_sku(false);

            //goods
            HashMap<String, String> map = new HashMap<>();
            map.put("picture", "../images/" + bookInfo.getBookIcon());

            bookSpecsVO.setGoods(map);
            bookSpecsVO.setSku(skuVO);
            return bookSpecsVO;
        }
    }

    @Transactional
    @Override
    public void subStock(Integer specsId, Integer quantity) {
        BookSpecs bookSpecs = specsRepository.findById(specsId).get();
        BookInfo bookInfo = bookRepository.findById(bookSpecs.getBookId()).get();
        int result = bookSpecs.getSpecsStock() - quantity;
        if (result < 0) {
            log.error("【库存异常】当前规格库存不足");
            throw new BookException(BookEnum.BOOK_STOCK_ERROR);
        }

        bookSpecs.setSpecsStock(result);
        specsRepository.save(bookSpecs);

        result = bookInfo.getBookStock() - quantity;
        if (result < 0) {
            log.error("【库存异常】总库存不足");
            throw new BookException(BookEnum.BOOK_STOCK_ERROR);
        }

        bookInfo.setBookStock(result);
        bookRepository.save(bookInfo);
    }

    @Override
    public void addStock(Integer specsId, Integer quantity) {
        BookSpecs bookSpecs = specsRepository.findById(specsId).get();
        BookInfo bookInfo = bookRepository.findById(bookSpecs.getBookId()).get();
        int result = bookSpecs.getSpecsStock() + quantity;
        bookSpecs.setSpecsStock(result);
        specsRepository.save(bookSpecs);
        result = bookInfo.getBookStock() + quantity;
        bookInfo.setBookStock(result);
        bookRepository.save(bookInfo);
    }
}
