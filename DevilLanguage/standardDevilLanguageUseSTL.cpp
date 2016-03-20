/*
*   Title:      魔王归来_STL
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
 
string str;
 
//打印结果
void print_result(string &str) {
    printf("魔王语言解释是：");
    for (string::iterator it = str.begin(); it != str.end(); it++) {
        if (islower(*it)) {
            cout << *it;
        } else if (*it == 'A') {
            printf("sae");
        } else if (*it == 'B') {
            printf("tsaedsae");
        } else {
            cout << *it;
        }
    }
 
    printf("\n魔王表达意思是: ");
    for (string::iterator it = str.begin(); it != str.end(); it++) {
        switch(*it) {
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
            default : cout << *it; break;
        }
    }
}
 
//括号内语言转换
void translate_to_human_anguage(string &str, int left, int right) {
    string source_str = str.substr(left + 1, right - left - 1);
    string target_str  = source_str.substr(0, 1);//首字符
    for (string::iterator it = source_str.end() - 1;
        it > source_str.begin(); it--) {
        target_str += *it + source_str.substr(0, 1);
    }
    str.replace(left, right - left + 1,  target_str);
}
 
//将所有括号展开
void remove_bracket(string &str) {
    if (str.find('(') == ULONG_MAX) {
        return ;
    }
 
    int right = str.find(')');//从前到后找第一个')'
    int left = str.rfind('(', right);//找到和')'匹配的'('
    if (right == left + 1) {//剔除单纯的()
        str.erase(left, 2);
    } else if (right == left + 2) {//括号内只有一个字符
        str.erase(right, 1); //先右后左，不然right就失效了
        str.erase(left, 1);
    } else {//一般情况
        translate_to_human_anguage(str, left, right);
    }
    remove_bracket(str);
}
 
int main(void) {
    puts("-----------------------------------------------");
    puts("提示：输入中只能包含 tdsaezgxnh、A、B字符。");
    puts("-----------------------------------------------\n");
    printf("请输入魔王语言：");
    str.clear();
 
    while (cin >> str) {
        remove_bracket(str);
        print_result(str);
        printf("\n\n请输入魔王语言：");
        str.clear();
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