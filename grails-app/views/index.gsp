<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<title>Grails Tooltip Plugin</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<style type="text/css">
		body { margin: 30px; }
		table { border-collapse: collapse; }
		td { border: 1px solid darkblue; padding:8px; }
	</style>
	<tooltip:resources/>
</head>

<body>
<h1>Grails Tooltip Plugin</h1>
<table>
	<tr>
		<td><tooltip:tip value="some tooltip text">a1</tooltip:tip></td>
		<td>a2</td>
		<td>a3</td>
		<td>a4</td>
	</tr>
		<tr onmouseover="tooltip.show('some tooltip text');" onmouseout="tooltip.hide();">
		<td>b1</td>
		<td>b2</td>
		<td>b3</td>
		<td>b4</td>
	</tr>
		<tr>
		<td>c1</td>
		<td>c2</td>
		<td>c3</td>
		<td>c4</td>
	</tr>
		<tr>
		<td>d1</td>
		<td>d2</td>
		<td>d3</td>
		<td><tooltip:tip code="sometxt">d4</tooltip:tip></td>
	</tr>
</table>
</body>

</html>