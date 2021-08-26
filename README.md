# javabank
Inspired by https://github.com/thejamesthomas/javabank. However, totally redeveloped.
In particular:
- mavenized
- adapted to java 11
- REST-client replaced with native java [HttpClient][1]  

Added support of some mountebank features that are missed in the original project:
- ability to create an imposter with [https][2] protocol
- behaviors: [wait][3] and [copy][5] 
- response parameters: [repeat][4]  
- predicate parameters: [caseSensitive][6], [xpath][7], [jsonpath][8] and [except][9]

[1]: https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html
[2]: http://www.mbtest.org/docs/protocols/https
[3]: http://www.mbtest.org/docs/api/behaviors#behavior-wait
[4]: http://www.mbtest.org/docs/api/stubs
[5]: http://www.mbtest.org/docs/api/behaviors#behavior-copy
[6]: http://www.mbtest.org/docs/api/predicates
[7]: http://www.mbtest.org/docs/api/xpath
[8]: http://www.mbtest.org/docs/api/jsonpath
[9]: http://www.mbtest.org/docs/api/predicates

