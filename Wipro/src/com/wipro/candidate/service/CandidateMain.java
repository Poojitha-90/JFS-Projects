package com.wipro.candidate.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;

public class CandidateMain {

    CandidateDAO dao = new CandidateDAO();

    // Add candidate
    public String addCandidate(CandidateBean candidate) {
        return dao.addCandidate(candidate);
    }

    // Display candidates
    public ArrayList<CandidateBean> displayAll(String criteria) {
        return dao.getByResult(criteria);
    }

    public static void main(String[] args) {

        CandidateMain service = new CandidateMain();
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.println("Enter candidate name:");
        String name = sc.nextLine();

        System.out.println("Enter marks for M1:");
        int m1 = sc.nextInt();

        System.out.println("Enter marks for M2:");
        int m2 = sc.nextInt();

        System.out.println("Enter marks for M3:");
        int m3 = sc.nextInt();
        sc.nextLine(); // consume newline

        // Calculate result & grade
        int total = m1 + m2 + m3;
        double avg = total / 3.0;

        String result;
        String grade;

        if (m1 >= 40 && m2 >= 40 && m3 >= 40) {
            result = "PASS";
            if (avg >= 75)
                grade = "A";
            else if (avg >= 60)
                grade = "B";
            else
                grade = "C";
        } else {
            result = "FAIL";
            grade = "NA";
        }

        // Create bean (NO ID)
        CandidateBean c = new CandidateBean();
        c.setName(name);
        c.setM1(m1);
        c.setM2(m2);
        c.setM3(m3);
        c.setResult(result);
        c.setGrade(grade);

        // Insert
        String status = service.addCandidate(c);
        System.out.println("Insert Status: " + status);

        // Display loop
        while (true) {
            System.out.println("\nEnter criteria (PASS / FAIL / ALL / EXIT):");
            String criteria = sc.nextLine();

            if ("EXIT".equalsIgnoreCase(criteria)) {
                System.out.println("Exiting program...");
                break;
            }

            ArrayList<CandidateBean> list = service.displayAll(criteria);

            if (list == null) {
                System.out.println("No candidates found");
            } else {
                for (CandidateBean cb : list) {
                    System.out.println(
                            cb.getId() + " - " +
                            cb.getName() + " - " +
                            cb.getM1() + ", " +
                            cb.getM2() + ", " +
                            cb.getM3() + " - " +
                            cb.getResult() + " - " +
                            cb.getGrade()
                    );
                }
            }
        }

        sc.close();
    }
}
