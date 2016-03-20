/*
*   Title:      魔王语言
*   Created on：2012-4-9
*   @author：    酷~行天下
*   @mail:      mindlee@me.com
*   @homepage   http://mindlee.net
*/
#include<iostream>
#include<cstdio>
#include<cstring>
#include<cctype>
#include<stack>
#include<queue>
using namespace std;
 
stack<char> S;
queue<char> Q;
char mo[1000];
 
//初始化数据
void init() {
    memset(mo, 0, sizeof(mo));
    while (!S.empty()) {
        S.pop();
    }
    while (!Q.empty()) {
        Q.pop();
    }
}
 
int main() {
    printf("请输入魔王语言：");
    init();
 
    while (gets(mo)) {
        //第一步，入栈
        for (int i = strlen(mo) - 1; i >= 0; i--) {
            S.push(mo[i]);
        }
 
        printf("魔王语言解释是：");
        while (!S.empty()) {
            char ch = S.top();
            S.pop();
 
            if (ch == 'A') {
                printf("sae");
            } else if (ch == 'B') {
                printf("tsaedsae");
            } else if (islower(ch)) {
                printf("%c", ch);
            } else if (ch == '(') {
                ch = S.top();//第一个字符
                S.pop();
                char tmp = ch;
                if (tmp == ')') continue;//排除掉只有（）时的情况 
 
                while (true) {//括号内所有字符入队列
                    ch = S.top();
                    S.pop();
                    if (ch == ')') {
                        break;
                    }
                    Q.push(ch);
                }
 
                //交替重新入栈
                S.push(tmp);
                while (!Q.empty()) {
                    S.push(Q.front());
                    S.push(tmp);
                    Q.pop();
                }
            } else  {
                puts("输入不合法啊，亲！！");
            }
        }
        printf("\n\n请输入魔王语言：");
        init();
    }
    return 0;
}
/*
请输入魔王语言：()
魔王语言解释是：
 
请输入魔王语言：(e)
魔王语言解释是：e
 
请输入魔王语言：(e)(e)
魔王语言解释是：ee
 
请输入魔王语言：(aen)B(xn)
魔王语言解释是：anaeatsaedsaexnx
 
请输入魔王语言：a(B)a
魔王语言解释是：atsaedsaea
*/