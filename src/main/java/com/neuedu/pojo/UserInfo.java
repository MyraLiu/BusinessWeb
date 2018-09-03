package com.neuedu.pojo;

public class UserInfo {
    private Integer id;// '用户表id',
    private String username;//  '用户名',
    private String password;//   '用户密码，MD5加密',
    private String email;//  用户邮箱
    private String phone;//  用户电话
    private String question;//   '找回密码问题',
    private String answer;//   '找回密码答案',
    private Integer role;//   '角色0-管理员，1-普通用户',
    private Long create_time;//   '创建时间',
    private Long update_time;//   '最后一次更新时间',
    private String token;//自动登录令牌

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", role=" + role +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", token='" + token + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getRole() {
        return role;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public String getToken() {
        return token;
    }
}
