package com.cr.util;

public class StateStack<E> {
	
	public Node<E> top;
	
	public StateStack(){
		top = null;
	}
	
	public void push(E elem){
		if(top == null){
			top = new Node<E>(elem,null);
		}else{
			Node<E> newTop = new Node<E>(elem, null);
			newTop.next = top;
			top = newTop;
		}
	}
	
	public void deleteTop(){
		if(top != null){
			Node<E> temp = top.next;
			top = temp;
		}
	}
	
	public E pop(E elem){
		return elem;
	}

}
