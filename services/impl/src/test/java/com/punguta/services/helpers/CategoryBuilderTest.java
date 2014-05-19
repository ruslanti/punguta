package com.punguta.services.helpers;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.punguta.domains.Category;
import com.punguta.services.helpers.json.JsonCategory;

import junit.framework.Assert;

/**
 * User: ruslan
 * Date: 5/19/14
 */
public class CategoryBuilderTest {

    @Test
    public void testBuilder() {
        final Category category1 = new CategoryBuilder()
                .setName("1")
                .addSubCategory(new CategoryBuilder().setName("1.1").withBudget(10))
                .addSubCategory(new CategoryBuilder().setName("1.2").withBudget(20)
                        .addSubCategory(new CategoryBuilder().setName("1.2.1"))
                        .addSubCategory(new CategoryBuilder().setName("1.2.2")))
                        .build();

        final Category category2 = new CategoryBuilder()
                .setName("2")
                .setAncestry(category1.getAncestry())
                .addSubCategory(new CategoryBuilder().setName("2.1").withBudget(10))
                .addSubCategory(new CategoryBuilder().setName("2.2").withBudget(20)
                        .addSubCategory(new CategoryBuilder().setName("2.2.1"))
                        .addSubCategory(new CategoryBuilder().setName("2.2.2"))
                        .addSubCategory(new CategoryBuilder().setName("2.2.3")
                            .addSubCategory(new CategoryBuilder().setName("2.2.3.1"))
                            .addSubCategory(new CategoryBuilder().setName("2.2.3.2")
                                            .addSubCategory(new CategoryBuilder().setName("2.2.3.2.1"))
                            )
                        ))
                .build();

        final Category root = category1.getAncestry();
        Assert.assertNull(root);

        Assert.assertEquals(2, category1.getChildren().size());

        for (Category category : category1.getChildren()) {
            if (category.getName().equals("1.1")) {
                Assert.assertEquals(0, category.getChildren().size());
            } else if (category.getName().equals("1.2")) {
                Assert.assertEquals(2, category.getChildren().size());
                for (Category category3 : category.getChildren()) {
                    if (category3.getName().equals("1.2.1")) {
                        Assert.assertEquals(0, category3.getChildren().size());
                    } else if (category3.getName().equals("1.2.2")) {
                        Assert.assertEquals(0, category3.getChildren().size());
                    } else {
                        Assert.failSame("unknown category " + category3.getName());
                    }
                }
            } else {
                Assert.failSame("unknown category " + category.getName());
            }
        }

        Assert.assertEquals(2, category2.getChildren().size());
        for (Category category : category2.getChildren()) {
            if (category.getName().equals("2.1")) {
                Assert.assertEquals(0, category.getChildren().size());
            } else if (category.getName().equals("2.2")) {
                Assert.assertEquals(3, category.getChildren().size());
                for (Category category3 : category.getChildren()) {
                    if (category3.getName().equals("2.2.1")) {
                        Assert.assertEquals(0, category3.getChildren().size());
                    } else if (category3.getName().equals("2.2.2")) {
                        Assert.assertEquals(0, category3.getChildren().size());
                    } else if (category3.getName().equals("2.2.3")) {
                        Assert.assertEquals(2, category3.getChildren().size());
                        for (Category category4 : category3.getChildren()) {
                            if (category4.getName().equals("2.2.3.1")) {
                                Assert.assertEquals(0, category4.getChildren().size());
                            } else if (category4.getName().equals("2.2.3.2")) {
                                Assert.assertEquals(1, category4.getChildren().size());
                                for (Category category5 : category4.getChildren()) {
                                    if (category5.getName().equals("2.2.3.2.1")) {
                                        Assert.assertEquals(0, category5.getChildren().size());
                                    } else {
                                        Assert.failSame("unknown category " + category5.getName());
                                    }
                                }
                            } else {
                                Assert.failSame("unknown category " + category4.getName());
                            }
                        }
                    } else {
                        Assert.failSame("unknown category " + category3.getName());
                    }
                }
            } else {
                Assert.failSame("unknown category " + category.getName());
            }
        }
    }

    @Test
    public void testLoadFromJson() {
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("categories.json");
        try {
            final CategoryBuilder categoryBuilder = CategoryBuilder.loadFromJson(inputStream);
            final Category category1 = categoryBuilder.build();
            final Category root = category1.getAncestry();
            Assert.assertNull(root);

            Assert.assertEquals(2, category1.getChildren().size());

            for (Category category : category1.getChildren()) {
                if (category.getName().equals("1.1")) {
                    Assert.assertEquals(0, category.getChildren().size());
                } else if (category.getName().equals("1.2")) {
                    Assert.assertEquals(2, category.getChildren().size());
                    for (Category category3 : category.getChildren()) {
                        if (category3.getName().equals("1.2.1")) {
                            Assert.assertEquals(0, category3.getChildren().size());
                        } else if (category3.getName().equals("1.2.2")) {
                            Assert.assertEquals(0, category3.getChildren().size());
                        } else {
                            Assert.failSame("unknown category " + category3.getName());
                        }
                    }
                } else {
                    Assert.failSame("unknown category " + category.getName());
                }
            }

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testWriteCategoriesToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonCategory jsonCategory = new JsonCategory();
        jsonCategory.setName("ROOT");
        jsonCategory.setChildren( new ArrayList<JsonCategory>());
        final JsonCategory sub1 = new JsonCategory();
        sub1.setName("sub1");
        jsonCategory.getChildren().add(sub1);

        String s = "";
        try {
            s = objectMapper.writeValueAsString(jsonCategory);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        System.out.println(s);
    }
}
