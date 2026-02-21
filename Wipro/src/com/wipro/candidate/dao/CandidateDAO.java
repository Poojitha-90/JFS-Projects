package com.wipro.candidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;

public class CandidateDAO {

    // Add Candidate
    public String addCandidate(CandidateBean cb) {
        String status = "FAIL";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getDBConnection();
            String sql = "INSERT INTO CANDIDATE_TBL (NAME, M1, M2, M3, RESULT, GRADE) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, cb.getName());
            ps.setInt(2, cb.getM1());
            ps.setInt(3, cb.getM2());
            ps.setInt(4, cb.getM3());
            ps.setString(5, cb.getResult());
            ps.setString(6, cb.getGrade());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = "SUCCESS";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    // Get Candidates by Result
    public ArrayList<CandidateBean> getByResult(String criteria) {
        ArrayList<CandidateBean> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getDBConnection();

            String sql;
            if ("PASS".equalsIgnoreCase(criteria)) {
                sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT='PASS'";
            } else if ("FAIL".equalsIgnoreCase(criteria)) {
                sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT='FAIL'";
            } else {
                sql = "SELECT * FROM CANDIDATE_TBL";
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CandidateBean cb = new CandidateBean();
                cb.setId(rs.getInt("ID"));
                cb.setName(rs.getString("NAME"));
                cb.setM1(rs.getInt("M1"));
                cb.setM2(rs.getInt("M2"));
                cb.setM3(rs.getInt("M3"));
                cb.setResult(rs.getString("RESULT"));
                cb.setGrade(rs.getString("GRADE"));
                list.add(cb);
            }

            return list.isEmpty() ? null : list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
