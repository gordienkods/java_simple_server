# java_simple_server

Require program rguments:
"85" "100" "192.168.1.219", where:
"85" - port for http server;
"100" - back log size;
"192.168.1.219" - trusted interfaces for hazelcast (possible to multiple settings like "192.168.1.219,192.168.1.218")

Requests examples:
GET: http://192.168.1.219/levelinfo/level_id;
GET: http://192.168.1.219/useringo/user_id
PUT:  http://192.168.1.219/setinfo BODY: {"userId":10,"levels_and_results":[{"1":999999}]} where:
levels_and_results - json array that consists from json objects, where first value - level_id, second - results.
