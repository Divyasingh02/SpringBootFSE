<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>FSE with Spring</title>
        <style type="text/css">
            .error {
                color: red;
            }
            table {
                width: 50%;
                border-collapse: collapse;
                border-spacing: 0px;
            }
            table td {
                border: 1px solid #565454;
                padding: 15px;
            }
            table button {
            	background-color: green;
            }
        </style>
    </head>
    <body>
        <h1>Subject</h1>
        <form:form action="searchSubject" method="post" modelAttribute="searchSubject">
            <table>
                <tr>
                    <td>Subtitle</td>
                    <td>
                        <form:input path="subtitle" /> <br />
                        <form:errors path="subtitle" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><button type="submit">Submit</button></td>
                </tr>
            </table>
        </form:form>
        
        <h2>Subject List</h2>
        <table>
            <tr>
                <td><strong>Subtitle</strong></td>
                <td><strong>Duration In Hours</strong></td>
            </tr>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td>${subject.subtitle}</td>
                    <td>${subject.durationInHours}</td>
                </tr>
            </c:forEach>
        </table>
        
        <h4><a href="/" style="float: left;">Home </a></h4>
    </body>
</html>