package service;

import dao.BookmarkGroupDao;
import model.BookmarkGroup;
import util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookmarkGroupService {
    private final BookmarkGroupDao bookmarkGroupDao;

    public BookmarkGroupService() {
        bookmarkGroupDao = new BookmarkGroupDao();
    }

    /**
     * 북마크 그룹 리스트 가져오기
     *
     * @return 북마크 그룹 리스트
     */
    public List<BookmarkGroup> getBookmarkGroupList() {
        List<BookmarkGroup> bookmarkGroupList;
        try (Connection conn = DbUtil.getConnection()) {
            BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
            bookmarkGroupList = bookmarkGroupDao.getBookmarkGroupList(conn);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 그룹 리스트를 가져오는 중 오류가 발생했습니다.", e);
        }
        return bookmarkGroupList;
    }

    /**
     * 북마크 그룹 생성
     *
     * @param bookmarkGroupName  북마크 그룹명
     * @param bookmarkGroupOrder 북마크 그룹 순서
     */
    public void addBookmarkGroup(String bookmarkGroupName, int bookmarkGroupOrder) {
        try (Connection conn = DbUtil.getConnection()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime now = LocalDateTime.now();
            String formattedPostDate = now.format(formatter);

            bookmarkGroupDao.addBookmarkGroup(conn, new BookmarkGroup(bookmarkGroupName, bookmarkGroupOrder, formattedPostDate));

        } catch (SQLException e) {
            throw new RuntimeException("북마크 그룹을 등록 하는 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 아이디로 특정 북마크 그룹 가져오기
     *
     * @param bookmarkGroupId 북마크 그룹 아이디
     * @return 북마크 그룹
     */

    public BookmarkGroup getBookmarkGroupDetail(int bookmarkGroupId) {
        BookmarkGroup bookmarkGroup;
        try (Connection conn = DbUtil.getConnection()) {
            bookmarkGroup = bookmarkGroupDao.getBookmarkGroupDetail(conn, bookmarkGroupId);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 그룹을 가져오는 중 오류가 발생했습니다.", e);
        }

        return bookmarkGroup;
    }

    /**
     * 북마크 그룹 수정
     *
     * @param bookmarkGroupId    아이디
     * @param bookmarkGroupName  그룹명
     * @param bookmarkGroupOrder 순서
     * @param postDate           등록일
     */
    public void updateBookmarkGroup(int bookmarkGroupId, String bookmarkGroupName, int bookmarkGroupOrder, String postDate) {
        try (Connection conn = DbUtil.getConnection()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime now = LocalDateTime.now();
            String editDate = now.format(formatter);

            BookmarkGroup bookmarkGroup = new BookmarkGroup(bookmarkGroupId, bookmarkGroupName, bookmarkGroupOrder, postDate, editDate);
            bookmarkGroupDao.updateBookmarkGroup(conn, bookmarkGroup);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 그룹을 수정하는 중 오류가 발생했습니다.", e);
        }
    }

    public void deleteBookmarkGroup(int bookmarkGroupId) {
        try (Connection conn = DbUtil.getConnection()) {
            bookmarkGroupDao.deleteBookmarkGroup(conn, bookmarkGroupId);
        } catch (SQLException e) {
            throw new RuntimeException("북마크 그룹을 삭제하는 중 오류가 발생했습니다.", e);
        }
    }
}
