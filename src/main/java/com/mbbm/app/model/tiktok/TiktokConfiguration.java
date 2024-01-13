package com.mbbm.app.model.tiktok;

import javax.persistence.*;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "tiktok_configuration")
public class TiktokConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "token_expired_at")
    private String tokenExpiredAt;

    @Column(name = "automate_acc_management")
    private boolean automateAccountManagement;


    public TiktokConfiguration(){}

}
