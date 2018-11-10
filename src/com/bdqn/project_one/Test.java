package com.bdqn.project_one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;
import java.text.NumberFormat;

public class Test {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		changePoint();
	}
	
	/**
	 * 进行加密或解密
	 */
	public static void encrption() {
		
		System.out.println("请输入一个英文字符串或解密字符串");
		String password = scan.nextLine();
		char [] array = password.toCharArray();
		for(int i=0;i<array.length;i++) {
			array[i]=(char)(array[i]^7);
		}
		System.out.println(new String(array));
	}
	
	/**
	 * 实现两个变量互换
	 * (不使用第三变量)
	 */
	public static void change() {
		int A = 50;
		int B = 25;
		A = A ^ B ;
		B = B ^ A ;
		A = A ^ B ;
		System.out.println( A + "\t" + B);
		
	}
	
	/**
	 * 使用while循环计算1+1/2!+1/3!...1/20!
	 */
	public static void mul() {
		BigDecimal sum = new BigDecimal(0.0);
		BigDecimal factorial = new BigDecimal(1.0);
		int i = 1;
		while(i<=20) {
			sum = sum.add(factorial);
			++i;
			factorial = factorial.multiply(new BigDecimal(1.0/i));
		}
		System.out.println("结果\n"+sum);
	}
	
	/**
	 * 打印空心的菱形
	 */
	public static void printHollow() {
		int row = scan.nextInt();
		if(row % 2 == 0) {
			row ++;
		}
		
		for(int i=0;i<row/2+1;i++) {
			for(int j=row/2+1;j>i+1;j--) {
				System.out.print(" ");
			}
			for(int j=0;j<2*i+1;j++) {
				if(j==0 || j==2*i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
		
		for(int i=row/2+1;i<row;i++) {
			for(int j=0;j<i-row/2;j++) {
				System.out.print(" ");
			}
			for(int j=0;j<2*row-1-2*i;j++) {
				if(j==0 || j==2*(row-i-1)) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * 将数字格式转换成货币字符串
	 */
	public static void NumberFormat() {
		double number = scan.nextDouble();
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.JAPANESE);
		System.out.println("Locale.JAPANESE:" + format.format(number));
	}
	
	public static void changePoint() {
		int [][] arr = {{1,2,3},{4,5,6},{7,8,9}};
		
		printArray(arr);
		int [][] arr2 = new int[arr.length][arr.length];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr2[i][j] = arr[j][i];
			}
		}
		printArray(arr2);
	}
	
	public static void printArray(int [][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 不用乘法实现2X16
	 */
	public static void example() {
		long number = scan.nextLong();
		System.out.println(number<<4);
	}

}
