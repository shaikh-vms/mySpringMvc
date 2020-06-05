<%@ include file="header.jspf"%>
<!-- we can create our own form but there is a way to associate a product object
to the form so data gets copied to fields automatically -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sForm"%>

<sForm:form modelAttribute="pr" action="save-product">
<sForm:hidden path="productId"/>
	<!-- associate model data from controller -->
	<!--<sForm:input path="productName"/> field of product object is associated there -->
	<div class="form-group row">
		<label class="col-md-4" for="productName">Product Name</label>
		<div class="col-md-8">
			<sForm:input path="productName" cssClass="form-control" />
			<sForm:errors path="productName" cssClass="text-danger" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-4" for="categoryId">Category</label>
		<div class="col-md-8">
			<sForm:select path="category.categoryId" cssClass="custom-select">
				<sForm:option value="-1" label="--Select--"></sForm:option>
				<sForm:options items="${categories }" itemLabel="categoryName"
					itemValue="categoryId" />
			</sForm:select>
			<sForm:errors path="category.categoryId" cssClass="text-danger" />
		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="supplierId">Supplier</label>
		<div class="col-md-8">
			<sForm:select path="supplier.supplierId" cssClass="custom-select">
				<sForm:option value="-1" label="--Select--"></sForm:option>
				<sForm:options items="${suppliers}" itemLabel="companyName"
					itemValue="supplierId" />
			</sForm:select>
			<sForm:errors path="supplier.supplierId" cssClass="text-danger" />

		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="unitPrice">Unit price</label>
		<div class="col-md-8">
			<sForm:input path="unitPrice" cssClass="form-control" />
			<sForm:errors path="unitPrice" cssClass="text-danger" />
			<!--  gives errors such as->--
			Failed to convert property value of type java.lang.String to required type java.lang.Double f
			or property unitPrice; nested exception is java.lang.NumberFormatException: For input string: "sesd" 
			so we can create a error property file to read customized errors error-messages.properties-->
		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="quantityPerUnit">Quantity per
			Unit</label>
		<div class="col-md-8">
			<sForm:input path="quantityPerUnit" cssClass="form-control" />
			<sForm:errors path="quantityPerUnit" cssClass="text-danger" />
		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="unitsInStock">Units in stock</label>
		<div class="col-md-8">
			<sForm:input path="unitsInStock" cssClass="form-control" />
			<sForm:errors path="unitsInStock" cssClass="text-danger" />

		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="unitsOnOrder">Units on order</label>
		<div class="col-md-8">
			<sForm:input path="unitsOnOrder" cssClass="form-control" />
			<sForm:errors path="unitsOnOrder" cssClass="text-danger" />
		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="reorderLevel">Reorder level</label>
		<div class="col-md-8">
			<sForm:input path="reorderLevel" cssClass="form-control" />
			<sForm:errors path="reorderLevel" cssClass="text-danger" />
		</div>
	</div>

	<div class="form-group row">
		<label class="col-md-4" for="discontinued">Discontinued</label>
		<div class="col-md-8">
			<label> 
			<sForm:radiobutton path="discontinued" value="1" />Yes
			</label> 
			<label> 
			<sForm:radiobutton path="discontinued" value="0" />No
			</label>
			<sForm:errors path="discontinued" cssClass="text-danger" />

		</div>
	</div>


	<div class="form-group row">
		<label class="col-md-4"></label>
		<div class="col-md-8">
			<button class="btn btn-primary">Submit changes</button>
		</div>
	</div>

</sForm:form>

<%@ include file="footer.jspf"%>