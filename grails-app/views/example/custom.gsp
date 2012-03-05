<!DOCTYPE html>
<html>

<head>
    <title>Custom Example</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${resource(dir: "css", file: "main.css")}"/>
    <tooltip:resources stylesheet="myTooltipStyles"/>
</head>

<body>
<h1>Custom Example</h1>

<div class="box">
    <tooltip:tip value="some tooltip text">Hover me!</tooltip:tip>
</div>

<div class="box">
    <tooltip:tip code="sometxt">Hover me!</tooltip:tip>
</div>

<div class="box">
    <span onmouseover="tooltip.show('some tooltip text');" onmouseout="tooltip.hide();">Hover me!</span>
</div>

<g:link controller="example"  action="index">Default Example</g:link>

</body>

</html>