<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Hello World</title>
    </head>
    <body>
        Hello ${name}
        <br />

        <table>
            <tr>
                <td>序号</td>
                <td>姓名</td>
                <td>年龄</td>
                <td>金额</td>
                <td>出生日期</td>
            </tr>
            <#--判断 stus 是否为空-->
            <#if stus??>
                <#--list指令-->
                <#list stus as stu>
                <tr>
                    <td>
                        ${stu_index + 1}
                    </td>
                    <td <#if stu.name == '小明'> style="background: red"</#if>>
                        ${stu.name}
                    </td>
                    <td>
                        ${stu.age}
                    </td>
                    <td>
                        ${stu.money}
                    </td>
                </tr>
                </#list>
            </#if>

        </table>

        <#--遍历 map-->
        姓名：${stuMap['stu1'].name}
        年龄：${stuMap['stu1'].age}

        <br/>
        姓名：${stuMap.stu2.name}
        年龄：${stuMap.stu2.age}

        <br />

        <#list stuMap?keys as k>
            姓名 ${stuMap[k].name}
            年龄 ${stuMap[k].age}
        </#list>
    </body>
</html>