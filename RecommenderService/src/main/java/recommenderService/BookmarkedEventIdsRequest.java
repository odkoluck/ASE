package recommenderService;

import java.util.List;

public class BookmarkedEventIdsRequest {
    private List<String> bookmarkedEventIds;

	public List<String> getBookmarkedEventIds() {
		return bookmarkedEventIds;
	}

	public void setBookmarkedEventIds(List<String> bookmarkedEventIds) {
		this.bookmarkedEventIds = bookmarkedEventIds;
	}
}
