package searchTeenavySport;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.jsoup.nodes.Element;

public class DoSomethingInAThread implements Runnable {
	Element div;
	CountDownLatch latch;
	String index;
	String folder;
	String tag;
	String link;
	Integer indexT;
	List<String> listDate;
	public DoSomethingInAThread(CountDownLatch latch,String link ) {
		this.latch = latch;
		this.link=link;
		
	}
	public DoSomethingInAThread(CountDownLatch latch,String link ,int indexT) {
		this.latch = latch;
		this.link=link;
		this.indexT=indexT;
	}
	public DoSomethingInAThread(CountDownLatch latch, Element div,String r,String tag,List<String> listDate) {
		this.latch = latch;
		this.div=div;
		this.index=r;
		this.tag=tag;
		this.listDate=listDate;
	}
	public DoSomethingInAThread(CountDownLatch latch, Element div,String r,String tag) {
		this.latch = latch;
		this.div=div;
		this.index=r;
		this.tag=tag;
		this.listDate=listDate;
	}
	public DoSomethingInAThread(CountDownLatch latch, Element div,String r,String tag,String folder) {
		this.latch = latch;
		this.div=div;
		this.index=r;
		this.tag=tag;
		this.folder=folder;
	}
	public void run() {
		try {
			System.out.println("Do some thing");
			latch.countDown();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
