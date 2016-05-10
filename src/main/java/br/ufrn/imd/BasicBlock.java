package br.ufrn.imd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class BasicBlock implements List<ProgramStatement>{

		private List<ProgramStatement> block;
		private int size;
		
		public BasicBlock(){
			block = new ArrayList<ProgramStatement>();
			this.size = 0;
		}
		
		public void addStatement(ProgramStatement statement){
			block.add(statement);
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return (size==0);
		}

		public boolean contains(Object o) {
			return block.contains(o);
		}

		public Iterator<ProgramStatement> iterator() {
			return null;
		}

		public Object[] toArray() {
			return block.toArray();
		}

		public <T> T[] toArray(T[] a) {
			return block.toArray(a);
		}

		public boolean add(ProgramStatement e) {
			return block.add(e);
		}

		public boolean remove(Object o) {
			return block.remove(o);
		}

		public boolean containsAll(Collection<?> c) {
			return block.containsAll(c);
		}

		public boolean addAll(Collection<? extends ProgramStatement> c) {
			return block.addAll(c);
		}

		public boolean addAll(int index, Collection<? extends ProgramStatement> c) {
			return block.addAll(c);
		}

		public boolean removeAll(Collection<?> c) {
			return block.removeAll(c);
		}

		public boolean retainAll(Collection<?> c) {
			return block.retainAll(c);
		}

		public void clear() {
			block.clear();
			
		}

		public ProgramStatement get(int index) {
			return block.get(index);
		}

		public ProgramStatement set(int index, ProgramStatement element) {
			return block.set(index, element);
		}

		public void add(int index, ProgramStatement element) {
			block.add(index, element);
		}

		public ProgramStatement remove(int index) {
			return block.remove(index);
		}

		public int indexOf(Object o) {
			return block.indexOf(o);
		}

		public int lastIndexOf(Object o) {
			return block.lastIndexOf(o);
		}

		public ListIterator<ProgramStatement> listIterator() {
			return block.listIterator();
		}

		public ListIterator<ProgramStatement> listIterator(int index) {
			return block.listIterator(index);
		}

		public List<ProgramStatement> subList(int fromIndex, int toIndex) {
			return subList(fromIndex, toIndex);
		}
	

}
