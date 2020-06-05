<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>${pageTitle}</h2>
<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>Sr. No.</th>
			<th>Product Name</th>
			<th>Unit Price</th>
			<th>Quantity per unit</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${products}" var="p" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td><a href="./product-details?id=${p.productId}" class ="btn btn-link">${p.productName}</a></td> <!-- getProductName from product is called -->
				<td>${p.unitPrice}</td>
				<td>${p.quantityPerUnit}</td>
			</tr>
		</c:forEach>
	</tbody>

	<!-- jstl java standard tag library to loop over the products from model
	to use we have to add some jars to project from ~TOMCAT-HOME~\webapps\examples\WEB-INF\lib-->
</table>
<%@ include file="./footer.jspf"%>