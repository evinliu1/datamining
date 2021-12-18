package datamining;
import java.util.*;
public class PageRank {
//defining our 5 nodes (web pages)
		static Node node1 = new Node();
		static Node node2 = new Node();
		static Node node3 = new Node();
		static Node node4 = new Node();
		static Node node5 = new Node();
//node constructor
		static public class Node
		{
			int page;
			Node refby;
			Node refby2;
			double reference;
			double value;
			double ovalue;
			public Node() {
				page = 0;
				refby = null;
				refby2 = null;
				reference = 0;
				value = 0;
				ovalue = 0;
				}
		}
//main method
		public static void main(String[] args)
		{
				double decay = 0.85;
				double base = 0.2;
				double end = ((1-decay)/5);
//allocating values to node properties
//page = how many pages reference said node
//refby = which specific nodes reference said node
//refby2 = which specific nodes reference said node (if more than one)
//reference = value at which this nodes references other nodes
				node1.page = 1;
				node1.reference = 1;
				node1.refby = node4;
				node2.page = 1;
				node2.refby = node1;
				node2.reference = 0.5;
				node3.page = 2;
				node3.reference = 1;
				node3.refby = node2;
				node3.refby2 = node5;
				node4.page = 2;
				node4.refby = node2;
				node4.refby2 = node3;
				node4.reference = 0.5;
				node5.page= 1 ;
				node5.reference = 1;
				node5.refby = node4;
//setting initial value of nodes at t=1
				node1.ovalue=value(node1, decay, base, end);
				node2.ovalue=value(node2, decay, base, end);
				node3.ovalue=value(node3, decay, base, end);
				node4.ovalue=value(node4, decay, base, end);
				node4.ovalue=value(node4, decay, base, end);
//runs power iteration 29 more times at decay of 0.85 to find page rank
				for (int i = 1 ; i < 30; i++) {
						value2(node1, decay, end);
						value2(node2, decay, end);
						value2(node3, decay, end);
						value2(node4, decay, end);
						value2(node5, decay, end);
						node1.ovalue=node1.value;
						node2.ovalue=node2.value;
						node3.ovalue=node3.value;
						node4.ovalue=node4.value;
						node5.ovalue=node5.value;
		}
//print out page rank of each node (web page)
		System.out.println("The page rank of web page #1 is " + node1.value);
		System.out.println("The page rank of web page #2 is " + node2.value);
		System.out.println("The page rank of web page #3 is " + node3.value);
		System.out.println("The page rank of web page #4 is " + node4.value);
		System.out.println("The page rank of web page #5 is " + node5.value);
}
/* this method run's each node through the power iteration method depending on if it is referenced by 2 or 1
pages*/
		public static double value2(Node node, double d, double e)
		{
			if (node.page == 1 ) { //if referenced by one page
				node.value = (d * node.refby.ovalue * node.refby.reference) + e;
			} else { //if referenced by two pages
				node.value = (d * node.refby.ovalue * node.refby.reference) + (d * node.refby2.ovalue *
				node.refby2.reference)+ e;
			}
			return node.value; //returns value to ovalue (old value)
		}
//method for finding initial value of node at t = 1
		public static double value(Node node, double d, double b, double e)
		{
			if (node.page == 1) {
				node.value = d * b * node.refby.reference + e;
			} else {
				node.value = (d * b * node.refby.reference) + (d * b * node.refby2.reference)+ e;
			}
			return node.value;
		}
}
