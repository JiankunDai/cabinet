


---

### 1.3.3. 我的信息

- GET /info/{studentId}

- return :

```json
{
    "code": 0,
    "data": {
        "number": 10,
        "location": "2_1"
    }
}
```

---


### 1.3.5. 获取书包柜数量

- GET /num/{floor}  

- return :

```json
{
    "code": 0,
    "data": [
        {
            "count": "105",
            "location": "2_11"
        },
        {
            "count": "105",
            "location": "2_2"
        },
        {
            "count": "120",
            "location": "2_8"
        },
        {
            "count": "60",
            "location": "2_4"
        },
        {
            "count": "105",
            "location": "2_1"
        },
        {
            "count": "60",
            "location": "2_7"
        },
        {
            "count": "60",
            "location": "2_6"
        },
        {
            "count": "105",
            "location": "2_9"
        },
        {
            "count": "120",
            "location": "2_10"
        },
        {
            "count": "60",
            "location": "2_5"
        },
        {
            "count": "120",
            "location": "2_3"
        },
        {
            "count": "105",
            "location": "2_12"
        }
    ]
}
```
### 1.3.6. 查看个人预约书包柜信息

- GET /info/{studentId}  

- return :
```json
{
    "code": 0,
    "data": {
        "number": 105,
        "location": "2_1"
    }
}

```

---



---



---





---





---