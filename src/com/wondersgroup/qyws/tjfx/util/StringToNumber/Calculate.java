package com.wondersgroup.qyws.tjfx.util.StringToNumber;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author yhh
 *
 */
public class Calculate {
	
	/**
	 * 将字符串转化成List
	 * @param str
	 * @return
	 */
	public ArrayList<String> getStringList(String str){
		ArrayList<String> result = new ArrayList<String>();
		String num = "";
		for (int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))||str.charAt(i)=='.'){
				num = num + str.charAt(i);
			}else{
				if(num != ""){
					result.add(num);
				}
				result.add(str.charAt(i) + "");
				num = "";
			}
		}
		if(num != ""){
			result.add(num);
		}
		return result;
	}
	
	/**
	 * 将中缀表达式转化为后缀表达式
	 * @param inOrderList
	 * @return
	 */
	public ArrayList<String> getPostOrder(ArrayList<String> inOrderList){
		
		ArrayList<String> result = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < inOrderList.size(); i++) {
			if(Character.isDigit(inOrderList.get(i).charAt(0))){
				result.add(inOrderList.get(i));
			}else{
				switch (inOrderList.get(i).charAt(0)) {
				case '(':
					stack.push(inOrderList.get(i));
					break;
				case ')':
					while (!stack.peek().equals("(")) {
						result.add(stack.pop());
					}
					stack.pop();
					break;
				default:
					while (!stack.isEmpty() && compare(stack.peek(), inOrderList.get(i))){
						result.add(stack.pop());
					}
					stack.push(inOrderList.get(i));
					break;
				}
			}
		}
		while(!stack.isEmpty()){
			result.add(stack.pop());
		}
		return result;
	}
	
	/**
	 * 计算后缀表达式
	 * @param postOrder
	 * @return
	 */
	public Float calculate(ArrayList<String> postOrder){
		Stack stack = new Stack();
		for (int i = 0; i < postOrder.size(); i++) {
			if(Character.isDigit(postOrder.get(i).charAt(0))){
				stack.push(Float.parseFloat(postOrder.get(i)));
			}else{
				Float back = (Float)stack.pop();
				Float front = (Float)stack.pop();
				Float res = 0.0f;
				switch (postOrder.get(i).charAt(0)) {
				case '+':
					res = front + back;
					break;
				case '-':
					res = front - back;
					break;
				case '*':
					res = front * back;
					break;
				case '/':
					res = front / back;
					break;
				}
				stack.push(res);
			}
		}
		return (Float)stack.pop();
	}
	
	
	 public static String transfrom(String exp){  
		 
          return exp.replaceAll("(\\(|^)-(\\d*)", "$10-$2");  
	 }  

	
	/**
	 * 比较运算符等级
	 * @param peek
	 * @param cur
	 * @return
	 */
	public static boolean compare(String peek, String cur){
		if("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
			return true;
		}else if("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
			return true;
		}else if("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
			return true;
		}else if("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		Calculate calculate = new Calculate();
		String s = "-2.1+1";
		String trans=transfrom(s);
		System.out.println(trans);
		ArrayList result = calculate.getStringList(trans);  //String转换为List
		result = calculate.getPostOrder(result);   //中缀变后缀
		float i = calculate.calculate(result);   //计算
		System.out.println(i);

	}
}
