package pl.legol.futstats.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dozer.Mapping;

@XmlRootElement(name = "Team")
public class TeamDto {
    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("searchName")
    private String searchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(id).append(name).append(searchName).build();
    }

    public static class Builder {
        private TeamDto inner;

        public Builder() {
            inner = new TeamDto();
        }

        public Builder named(String name, String searchName) {
            inner.setName(name);
            inner.setSearchName(searchName);
            return this;
        }

        public TeamDto build() {
            return inner;
        }
    }

}
