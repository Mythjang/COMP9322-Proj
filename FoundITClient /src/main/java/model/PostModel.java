package model;

import java.util.List;

public class PostModel {

    public PostModel(){

    }

    private String title;

    private List<String> options;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "title='" + title + '\'' +
                ", options=" + options +
                '}';
    }
}
