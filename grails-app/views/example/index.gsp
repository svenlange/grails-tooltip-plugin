<!DOCTYPE html>
<html>

<head>
    <title>Default Example</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${resource(dir: "css", file: "main.css")}"/>
    <tooltip:resources/>
</head>

<body>
<h1>Default Example</h1>

<div class="box">
    <tooltip:tip value="some tooltip text">Hover me!</tooltip:tip>
</div>

<div class="box">
    <tooltip:tip code="sometxt">Hover me!</tooltip:tip>
</div>

<div class="box">
    <span onmouseover="tooltip.show('some tooltip text');" onmouseout="tooltip.hide();">Hover me!</span>
</div>

<g:link controller="example" action="custom">Custom Example</g:link>

</body>

</html>