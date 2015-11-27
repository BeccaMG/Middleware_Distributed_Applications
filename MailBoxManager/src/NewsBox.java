package mailBoxManager;

import javax.persistence.*;

@Entity
@Table(name="BOX")
public class NewsBox extends Box {

    public NewsBox() {
        super();
        this.name = "NewsBox";
    }

}