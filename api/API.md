###1、首页数据

```
GET /book/index
```

参数

```json
无
```

返回

```json
{
  code: 2000,
  msg: "执行成功",
  data: {
    categories: [
      {
       "name": "Python",
        "type": 1
      }
    ],
  "books": [
      {
        "id": 1,
        "title": "Python编程 从入门到实践",
        "price": "65.00",
        "desc": "人民邮电出版社",
        "tag": [
          {
            "name": "(美)埃里克·马瑟斯（Eric Matthes） 著 "
          },
          {
            "name": "人民邮电出版社 "
          },
          {
            "name": "2016-07"
          }
        ],
        "thumb": "../images/0049c52a-9800-48d9-986c-d88ea8720cf6.jpg"
      }
    ]
  }
}
```

###2、根据类型查询图书

```
GET /book/getBookByCategoryType/{categoryType}
```

参数

```json
categoryType: 1
```

返回

```json
{
 "code": 2000,
  "msg": "执行成功",
  "data": [
    {
      "id": 1,
      "title": "Python编程 从入门到实践",
      "price": "65.00",
      "desc": "人民邮电出版社",
      "tag": [
        {
          "name": "(美)埃里克·马瑟斯（Eric Matthes） 著 "
        },
        {
          "name": "人民邮电出版社 "
        },
        {
          "name": "2016-07"
        }
      ],
      "thumb": "../images/0049c52a-9800-48d9-986c-d88ea8720cf6.jpg"
    }
  ]
}
```

###3、查询图书规格

```
GET /book/getSpecsByBookId/{bookId}
```

参数

```json
bookId: 1
```

返回

```json
{
  "code": 2000,
  "msg": "执行成功",
  "data": {
    "goods": {
      "picture": "../images/0049c52a-9800-48d9-986c-d88ea8720cf6.jpg"
    },
    "sku": {
      "tree": [
        {
          "k": "选项",
          "v": [
            {
              "id": 1,
              "name": "【单件】Python编程 从入门到实践",
              "imgUrl": "../images/f7ceacb6-bafe-453f-aeb2-c678bc11d6d0.jpg",
              "previewImgUrl": "../images/f7ceacb6-bafe-453f-aeb2-c678bc11d6d0.jpg"
            },
            {
              "id": 2,
              "name": "【套装3件】Python编程三剑客：Python编程从入门到实践+Python编程快速上手+Python极客项目编程（京东套装共3册）",
              "imgUrl": "../images/2d6ffdf6-f9df-4773-849c-e30a13e77f91.jpg",
              "previewImgUrl": "../images/2d6ffdf6-f9df-4773-849c-e30a13e77f91.jpg"
            },
            {
              "id": 3,
              "name": "【套装2件】精通Python编程两件套：Python编程从入门到实践+Python数据结构与算法分析 第2版（京东套装共2册）",
              "imgUrl": "../images/8c44d71c-cb78-4652-a1aa-ccbbe7730b3a.jpg",
              "previewImgUrl": "../images/8c44d71c-cb78-4652-a1aa-ccbbe7730b3a.jpg"
            }
          ],
          "k_s": "s1"
        }
      ],
      "list": [
        {
          "s1": 1,
          "price": 6500,
          "stock_num": 3
        },
        {
          "s1": 2,
          "price": 11375,
          "stock_num": 4
        },
        {
          "s1": 3,
          "price": 19775,
          "stock_num": 2
        }
      ],
      "price": "65.00",
      "stock_num": 9,
      "none_sku": false,
      "hide_stock": false
    }
  }
}
```

###4、查询地址

```
GET /address/list
```

参数

```json
无
```

返回

```json
{
  "code": 2000,
  "msg": "执行成功",
  "data": [
    {
      "id": 36,
      "areaCode": "110101",
      "name": "张三",
      "tel": "17777777777",
      "address": "北京市北京市东城区123街123号"
    }
  ]
}
```

### 5、创建地址

```
POST /address/create
```

参数

```json
{
	"name": "张三",
	"tel": "17777777777",
	"country": "",
	"province": "北京市",
	"city": "北京市",
	"county": "东城区",
	"areaCode": "110101",
	"postalCode": "",
	"addressDetail": "123街123号",
	"isDefault": false
}
```

返回

```json
{
    code: 2001,
    msg: "添加成功",
    data: null
}
```

### 6、修改地址

```
PUT /address/update
```

参数

```json
{
	"name": "李四",
	"tel": "17777777777",
	"country": "",
	"province": "北京市",
	"city": "北京市",
	"county": "东城区",
	"areaCode": "110101",
	"postalCode": "",
	"addressDetail": "123街123号",
	"isDefault": false,
	"id": 37,
	"address": "北京市北京市东城区123街123号"
}
```

返回

```json
{
    code: 2003,
    msg: "修改成功",
    data: null
}
```

###7、创建订单

```
POST /order/create
```

参数

```json
{
	"name": "张三",
	"tel": "17777777777",
	"address": "北京市北京市东城区123街123号",
	"specsId": 1,
	"quantity": 1
}
```

返回

```json
{
	"code": 2001,
	"msg": "添加成功",
	"data": {
		"orderId": "1591444530567676377"
	}
}
```

###8、订单详情

```
GET /order/detail/{orderId}
```

参数

```json
orderId: "1591444530567676377"
```

返回

```json
{
	"code": 2000,
	"msg": "执行成功",
	"data": {
		"orderId": "1591444530567676377",
		"buyerName": "张三",
		"tel": "17777777777",
		"address": "北京市北京市东城区123街123号",
		"num": 1,
		"bookQuantity": null,
		"bookName": "Python编程 从入门到实践",
		"specs": "【单件】Python编程 从入门到实践",
		"price": "65.00",
		"icon": "../images/f7ceacb6-bafe-453f-aeb2-c678bc11d6d0.jpg",
		"amount": 75.0,
		"payStatus": 1,
		"freight": 10
	}
}
```

###9、支付订单

```
PUT /order/pay/{orderId}
```

参数

```json
orderId: "1591444530567676377"
```

返回

```json
{
  code: 0
  msg: "成功"
  data: {
      orderId: "1591444530567676377"
  }
}
```


