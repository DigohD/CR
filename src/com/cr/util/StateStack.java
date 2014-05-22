package com.cr.util;

public class StateStack<E> {
	
	public Node<E> head;
	public Node<E> next;
	
	public StateStack(){
		head = null;
		next = null;
	}
	
	public void push(E elem){
		if(head == null){
			head = new Node<E>(elem,next);
		}else{
			while(next != null){
				
			}
		}
		
	}
	
	public void pop(E elem){
		
	}

}
