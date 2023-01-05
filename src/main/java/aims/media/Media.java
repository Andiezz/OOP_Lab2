package aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
    protected int id = 0;
    protected String title;
    protected String category;
    protected float cost;
    protected static int nbProducts = 0;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparatorByCostTitle();

    public Media() {}

    public Media(String title) {
        nbProducts++;
        this.id = nbProducts;
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        nbProducts++;
        this.id = nbProducts;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public abstract String toString();


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Media product) {
            if (product.getTitle().equals(this.getTitle())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean filterProperty(String filter, String type) {
        if (filter == null || filter.isEmpty()) {
            return true;
        } else {
            if (Objects.equals(type, "title")) {
                return this.getTitle().toLowerCase().indexOf(filter.toLowerCase()) != -1;
            } else if (Objects.equals(type, "id")) {
                return Integer.toString(this.getId()).toLowerCase().indexOf(filter.toLowerCase()) != -1;
            }
        }
        return false;
    }

}
