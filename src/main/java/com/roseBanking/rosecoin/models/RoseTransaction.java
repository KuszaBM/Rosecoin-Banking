package com.roseBanking.rosecoin.models;

import jakarta.persistence.*;

import java.sql.Date;

public class RoseTransaction {


    private Long id;
    private User from;
    private User to;
    private int coins;
    private Date transactionDate;
}
