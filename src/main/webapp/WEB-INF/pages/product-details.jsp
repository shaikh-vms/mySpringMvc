<%@ include file="./header.jspf"%>
<h2>Product Details for id ${param.id}</h2>
<a href="./edit-product?id=${prDtl.productId}" class="btn btn-primary">Edit</a>
<div class="row">
	<div class="col">
		<table class="table">
			<tbody>
				<tr>
					<td>Name</td>
					<td>${prDtl.productName}</td>
				</tr>
				<tr>
					<td>Category</td>
					<td>${prDtl.category.categoryName}</td>
				</tr>
				<tr>
					<td>Supplier</td>
					<td>${prDtl.supplier.companyName}</td>
				</tr>
				<tr>
					<td>Unit Price</td>
					<td>${prDtl.unitPrice}</td>
				</tr>
				<tr>
					<td>Quantity per unit</td>
					<td>${prDtl.quantityPerUnit}</td>
				</tr>
				<tr>
					<td>Units in Stock</td>
					<td>${prDtl.unitsInStock}</td>
				</tr>
				<tr>
					<td>Units on order</td>
					<td>${prDtl.unitsOnOrder}</td>
				</tr>
				<tr>
					<td>Reorder level</td>
					<td>${prDtl.reorderLevel}</td>
				</tr>
				<tr>
					<td>Discontinued?</td>
					<td>${prDtl.discontinued==1 ? "Yes":"No"}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="col"></div>
</div>
<%@ include file="./footer.jspf"%>