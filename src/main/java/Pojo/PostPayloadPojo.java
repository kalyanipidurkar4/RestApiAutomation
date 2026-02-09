package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostPayloadPojo {

    private String job;
    private String name;
    List<String> Language=new ArrayList<>();
    List<CityPojo> City=new ArrayList<>();

    public List<CityPojo> getCitypojoList() {
        return City;
    }

    public void setCitypojoList(List<CityPojo> citypojoList) {
        this.City = citypojoList;
    }

    public List<String> getLanguage() {
        return Language;
    }

    public void setLanguage(List<String> language) {
        this.Language = language;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
