<html>
	<head>
		<title>test</title>
	</head>
	<body>
		<#settion number_format="currency">
		<#assign answer=42>
		${answer}
		${answer?string}<#--The same as ${answer} -->
		${answer?string.number}
		${answer?string.percent}
		${answer?string.currency}
		${answer}
	<#--,插值结果为日期值:根据默认格式(由#setting指令设置)将表达式结果转换成文本输出.
		 可以使用内建的字符串函数格式化单个插值,-->
		${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")}
		${lastUpdated?string("EEE, MMM d, ''yy")}
		${lastUpdated?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")}
	<#---->
	</body>
</html>