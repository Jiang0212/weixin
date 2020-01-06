package entify;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
	
	@XStreamAlias("ArticleCount")
	private String articleCount;
	
	@XStreamAlias("Articles")
	private java.util.List<Article> articles = new ArrayList<Article>();
	
	public NewsMessage(Map<String, String> requestMap) {
		super(requestMap);
	}

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public java.util.List<Article> getArticles() {
		return articles;
	}

	public void setArticles(java.util.List<Article> articles) {
		this.articles = articles;
	}

	public NewsMessage(Map<String, String> requestMap,List<Article> articles) {
		super(requestMap);
		setMsgType("news");
		this.articleCount = articles.size()+"";
		this.articles = articles;
	}
	
}
