/*
*   Title:      魔王归来_Stack
*   Created on：2012-4-10
*   @author：    酷~行天下
*   @mail:      mindlee@me.com
*   @homepage   http://mindlee.net
*/
#include<iostream>
#include<cstdio>
#include<cstring>
#include<string>
#include<cctype>
#include<climits>
#include<stack>
#include<queue>
using namespace std;
 
stack<char> S, S1, S2;
string str;
char devil[10000];
char person[10000];
bool contain_bracket = true;
 
//初始化数据
void init() {
    memset(devil , 0, sizeof(devil ));
    memset(person , 0, sizeof(person ));
    str.clear();
    while (!S.empty()) S.pop();
    while (!S1.empty()) S1.pop();
    while (!S2.empty()) S2.pop();
}
 
//打印字符结果
void print_letter () {
    printf("魔王语言解释是：");
    int len = strlen(person );
    for (int i = 0; i < len; i++) {
        char ch = person [i];
        if (ch == 'A') {
            printf("sae");
        } else if (ch == 'B') {
            printf("tsaedsae");
        } else if (islower(ch)) {
            printf("%c", ch);
        } else  {
            printf("%c", ch);
        }
    }
}
 
//打印汉字结果
void print_person () {
    printf("\n魔王表达意思是: ");
    int len = strlen(person );
    for (int i = 0; i < len; i++) {
        char ch = person [i];
        switch(ch) {
            case 't' : printf("天"); break;
            case 'd' : printf("地"); break;
            case 's' : printf("上"); break;
            case 'a' : printf("一只"); break;
            case 'e' : printf("鹅"); break;
            case 'z' : printf("追"); break;
            case 'g' : printf("赶"); break;
            case 'x' : printf("下"); break;
            case 'n' : printf("蛋"); break;
            case 'h' : printf("恨"); break;
            case 'A' : printf("上一只鹅"); break;
            case 'B' : printf("天上一只鹅地上一只鹅"); break;
            default : printf("%c", ch);; break;
        }
    }
    puts("");
}
 
int main() {
    puts("-----------------------------------------------");
    puts("提示：输入中只能包含 tdsaezgxnh、A、B字符。");
    puts("-----------------------------------------------\n");
    printf("请输入魔王语言：");
    init();
 
    while (cin >> str) {
        string::iterator it = str.begin();
        while (it != str.end()) {//过滤字符串中特例
            if (*it == '(' && *(it + 1) == ')') {//过滤挨着的()
                str.erase(it, it + 2);
                it = str.begin();
            } else if (*it == '(' && isalpha(*(it + 1)) && *(it + 2) == ')'){
                str.erase(it + 2);//过滤括号内只有一个字符,比如(e)
                str.erase(it);
                it = str.begin();
            } else {
                it++;
            }
        }   
 
        if (str.find('(') == ULONG_MAX) {//压根没括号了
            strcpy(person, str.c_str());
            print_letter();
            print_person();
            printf("\n请输入魔王语言：");
            init();
            continue;
        }
 
        strcpy(devil, str.c_str());
        //第一步，入栈
        for (int i = strlen(devil ) - 1; i >= 0; i--) {
            S.push(devil [i]);
        }
 
        while (!S.empty()) {
            while (true) {//字符进入S1
                char ch = S.top();
                S.pop();
                if (ch == ')') break;
                S1.push(ch);
            }
 
            while (!S1.empty()) {//从S1进入S2
                char ch = S1.top();
                S1.pop();
                if (ch == '(') break;
                S2.push(ch);
            }
 
            char tmp = S2.top();
            S2.pop();
            S.push(tmp);
            while (!S2.empty()) {//S2放回初始栈S
                char ch = S2.top();
                S2.pop();
                S.push(ch);
                S.push(tmp);
            }
 
            while (!S1.empty()) {//S1放回初始栈S
                char ch = S1.top();
                S1.pop();
                S.push(ch);
            }
 
            contain_bracket = false;
            memset(person, 0, sizeof(person));
            for (int i = 0; i < 10000; i++) {//存到数组&检测是否完结
                if (!S.empty()) {
                    char ch = S.top();
                    S.pop();
                    if (ch == ')') {
                        contain_bracket = true;
                    }
                    person [i] = ch;
                } else {
                    break;
                }
            }
 
            for (int i = strlen(person) - 1; i >= 0; i--) {
                S.push(person [i]);
            }
 
            if (!contain_bracket) {//如果没括号，跳出
                break;
            }
        }//while 
 
        print_letter();
        print_person();
 
        printf("\n请输入魔王语言：");
        init();
    }
    return 0;
}
/*
-----------------------------------------------
提示：输入中只能包含 tdsaezgxnh、A、B字符。
-----------------------------------------------
 
请输入魔王语言：()
魔王语言解释是：
魔王表达意思是:
 
请输入魔王语言：(())
魔王语言解释是：
魔王表达意思是:
 
请输入魔王语言：((()))
魔王语言解释是：
魔王表达意思是:
 
请输入魔王语言：(()())
魔王语言解释是：
魔王表达意思是:
 
请输入魔王语言：(e)
魔王语言解释是：e
魔王表达意思是: 鹅
 
请输入魔王语言：((e))
魔王语言解释是：e
魔王表达意思是: 鹅
 
请输入魔王语言：((e)(e))
魔王语言解释是：eee
魔王表达意思是: 鹅鹅鹅
 
请输入魔王语言：(aen)
魔王语言解释是：anaea
魔王表达意思是: 一只蛋一只鹅一只
 
请输入魔王语言：(aen)B(xn)
魔王语言解释是：anaeatsaedsaexnx
魔王表达意思是: 一只蛋一只鹅一只天上一只鹅地上一只鹅下蛋下
 
请输入魔王语言：((aen)B(xn))
魔王语言解释是：axanaxatsaedsaeaaaeaaana
魔王表达意思是: 一只下一只蛋一只下一只天上一只鹅地上一只鹅一只一只一只鹅一只一只
一只蛋一只
 
请输入魔王语言：a(B)a
魔王语言解释是：atsaedsaea
魔王表达意思是: 一只天上一只鹅地上一只鹅一只
 
请输入魔王语言：a(AB)a
魔王语言解释是：asaetsaedsaesaea
魔王表达意思是: 一只上一只鹅天上一只鹅地上一只鹅上一只鹅一只
*/