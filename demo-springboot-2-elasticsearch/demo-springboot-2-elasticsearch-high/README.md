PUT /sys_user
{
  "mappings": {
    "_doc": {
	  "properties": {
        "id": {"type": "long"},
        "userCode": {"type": "keyword"},
        "username": {"type": "keyword"},
        "firstName": {"type": "keyword"},
        "lastName": {"type": "keyword"},
        "fullName": {"type": "text"}
	  }
	}
  }
}

# 博客评论父子文档
## blog文档
POST my_blogs/_doc/20200101
{
  "title": "谁是最可爱的人",
  "content": "在朝鲜的每一天，我都被一些东西感动着；我的思想感情的潮水，在放纵奔流着；我想把一切东西都告诉给我祖国的朋友们。但我最急于告诉你们的，是我思想感情的一段重要经历，这就是：我越来越深刻地感觉到谁是我们最可爱的人！",
  "blog_comments_relation": {
    "name": "blog"
  }
}

## 评论文档
POST my_blogs/_doc/20200201?routing=20200101
{
  "username": "王可",
  "comment": "写得真不错",
  "blog_comments_relation": {
    "name": "comment",
    "parent": 20200101
  }
}

POST my_blogs/_doc/20200202?routing=20200101
{
  "username": "刘峰",
  "comment": "读了让人很感动的文字",
  "blog_comments_relation": {
    "name": "comment",
    "parent": 20200101
  }
}

# 根据父文档ID查询
GET my_blogs/_doc/20200101

# 根据ParenID查询
GET my_blogs/_search
{
  "query": {
    "parent_id": {
      "type": "comment",
      "id": "20200101"
    }
  }
}

PUT user_info
{
  "settings": {
    "number_of_replicas": 1,
    "number_of_shards": 1
  }, 
  "mappings": {
    "_doc": {
      "properties": {
        "user": {
          "properties": {
            "id": {"type": "long"},
            "userCode": {"type": "keyword"},
            "username": {"type": "keyword"},
            "firstName": {"type": "keyword"},
            "lastName": {"type": "keyword"},
            "fullName": {"type": "text"}
          }
        },
        "role": {
          "properties": {
            "id": {"type": "long"},
            "roleCode": {"type": "keyword"},
            "roleName": {"type": "text"}
          }
        },
        "user_role": {
          "properties": {
            "userCode": {"type": "keyword"},
            "roleCode": {"type": "keyword"}
          }
        }
      }
    }
  }
}

# 添加人员
{
	"userDTO": {
		"id": 1,
		"userCode": "20200801",
		"username": "wangke",
		"firstName": "可",
		"lastName": "王",
		"fullName": "王可"
	}
}

{
	"userDTO": {
		"id": 2,
		"userCode": "20200802",
		"username": "lisha",
		"firstName": "沙",
		"lastName": "李",
		"fullName": "李沙"
	}
}

{
	"userDTO": {
		"id": 3,
		"userCode": "20200803",
		"username": "liufeng",
		"firstName": "峰",
		"lastName": "刘",
		"fullName": "刘峰"
	}
}

# 添加角色
{
	"roleDTO": {
		"id": 1,
		"roleCode": "role_admin",
		"roleName": "管理员"
	}
}

{
	"roleDTO": {
		"id": 2,
		"roleCode": "role_operator",
		"roleName": "操作员"
	}
}

# 人员角色关系
{
	"userRoleDTO": {
		"userCode": "20200801",
		"roleCode": "admin"
	}
}

{
	"userRoleDTO": {
		"userCode": "20200801",
		"roleCode": "role_operator"
	}
}

{
	"userRoleDTO": {
		"userCode": "20200802",
		"roleCode": "role_operator"
	}
}

{
	"userRoleDTO": {
		"userCode": "20200803",
		"roleCode": "role_operator"
	}
}

# 执行查询
1. 查询人员和角色信息
GET user_info/_search
{
  "query": {
    "bool": {
      "should": [
        {"term": {"userDTO.userCode": "20200801"}},
        {"term": {"userRoleDTO.userCode": "20200801"}}
      ]
    }
  }
}

2. 查询角色下所有用户
