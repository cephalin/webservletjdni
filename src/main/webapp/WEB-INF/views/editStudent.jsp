<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<base href="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Edit student ${studentRecord.id}</h1>
        <div class="card">
            <div class="card-body">
                <form action="edit" method="post">
                    <input type="hidden" name="id" value="${studentRecord.id}">

                    <div class="form-group row">
                        <label for="name" class="col-sm-2 col-form-label">Name</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="name" value="${studentRecord.name}">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="std" class="col-sm-2 col-form-label">Std</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="std" value="${studentRecord.std}">
                        </div>
                    </div>

                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/" role="button">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>