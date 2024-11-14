<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>/views/file/form.jsp</title>
        <!-- jQuery CDN 추가 -->
        <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

    </head>

    <body>
        <h1>/views/file/form.jsp</h1>

        <fieldset>
            <legend>파일업로드</legend>
            <form action="${contextPath}/file/upload" method="post" enctype="multipart/form-data">
                아이디 : <input type="text" name="userid" id=""> <br>
                이름 : <input type="text" name="username" id=""> <br>
                <hr>
                첨부파일 : <input type="file" name="userfile"> <br>
                <div id="divFile"></div>
                <input type="button" value="파일추가" id="btnAdd">
                <input type="submit" value="파일업로드">
            </form>
        </fieldset>

        <script>
            $(function () {
                var cnt = 1;
                // 파일추가 버튼 클릭시
                $("#btnAdd").click(function () {
                    // 파일추가(input 태그) 동적으로 생성
                    $("#divFile").append('<br> <input type="file" name="userfile' + cnt + '"></input>');
                    cnt++;
                });
            });
        </script>
    </body>

    </html>