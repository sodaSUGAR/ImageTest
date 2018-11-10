package com.bdqn.project_one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection extends BaseDao {

	public static void main(String[] args) throws IOException, SQLException {
		ReadImage();
	}

	public static void initData() throws SQLException, IOException {

		// 初始化连接
		Connection conn = getConnection();
		// 声明PreparedStatement
		PreparedStatement pstmt = null;

		InputStream in= ImageUtil.getImageByte("E:\\SUGAR\\Mars\\S2Project\\WebContent\\static\\image\\3d4820d3e15664d.jpg");

		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO photo (name,src) VALUES (?,?)");

		pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, "饭饭");
		pstmt.setBinaryStream(2, in,in.available());

		pstmt.executeUpdate();

	}

	private static File file = null;

	public static FileInputStream getImageByte(String infile) throws FileNotFoundException {
		FileInputStream imageByte = null;
		file = new File(infile);
		imageByte = new FileInputStream(file);
		return imageByte;
	}
	
	public static void ReadImage() throws SQLException {
		// 初始化连接
		Connection conn = getConnection();
		// 声明PreparedStatement
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        InputStream ls = null;
		
		 String sql = "select p.src from photo p where id = 1";
		 pstmt = conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		 while(rs.next()) {
			 ls = rs.getBinaryStream("src");
			 ImageUtil.readBlob(ls, "D:\\123.jpg");
		 }
	}

}
