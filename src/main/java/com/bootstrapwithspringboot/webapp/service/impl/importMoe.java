package com.bootstrapwithspringboot.webapp.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bootstrapwithspringboot.webapp.model.School;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class importMoe {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/sg";
        String username = "root";
        String password = "test";
 
        String csvFilePath = "/Users/hd/Downloads/spring/bootstrap-with-springboot-master/";
        String curName=csvFilePath+"moe20201.csv";
        System.out.println(curName);
        extracted(jdbcURL, username, password, curName);
       
    }

    private static void extracted(String jdbcURL, String username, String password, String csvFilePath) {
        int batchSize = 20;

        Connection connection = null;

        ICsvBeanReader beanReader = null;
        CellProcessor[] processors = new CellProcessor[] { new NotNull(), // course name
                new NotNull(), // student name
                new Optional(), // timestamp
                new Optional(), // rating
                new Optional(), // comment,
                new Optional()// comment
        };

        try {
            long start = System.currentTimeMillis();

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO School (School, Year, Phase, Availiability, Registration,Sucess_Rate) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);

            beanReader.getHeader(false); // skip header line

            String[] header = { "School", "Year", "Phase", "Availiability", "Registration", "SucessRate" };
            School bean = null;

            int count = 0;

            while ((bean = beanReader.read(School.class, header, processors)) != null) {
                String School = bean.getSchool();
                String Year = bean.getYear();
                String Phase = bean.getPhase();
                String Availiability = bean.getAvailiability();
                String Registration = bean.getRegistration();
                String rate = bean.getSucessRate();

                statement.setString(1, School);
                statement.setString(2, Year);

                statement.setString(3, Phase);

                statement.setString(4, Availiability);

                statement.setString(5, Registration);
                statement.setString(6, rate);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            beanReader.close();

            // execute the remaining queries
            statement.executeBatch();

            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.println("Execution Time: " + (end - start));
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}