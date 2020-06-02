# SU에서 EOF 사용시 내부 변수 처리 방법

`$`를 이용하면 변수값을 사용할 수 있는데, su에서 EOF 를 사용시 `$`가 인식되지 않았다..

해결방법은 생각보다 간단했다..

내부변수를 사용하려면 `$` 변수 앞에 '\'문자를 추가하여 `\$` 이런 식으로 해야 문자를 인식하게 된다.

```shell
sudo su -l user << EOF

outdata1=""
get_result_func() {
outdata1='helloworld'
echo 
}
get_result_func
echo "#outdata1="\$outdata1
```