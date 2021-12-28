/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

/**
 *
 * @author Aravindhanj
 */
public class ProjectActivityReport {
    
   private  String project_Id;
   private  String customer;
   private String division_Id;
   private String division;
   private String author_Id;
   private String author;
   private String title;
   private String planner;
   private String color_Id;
   private String color;
   private String trim_Size;
   private String award_Date;
   private String due_Date;
   private String date_Shipped;
   private double estiamted_Selling_Value;
   private String partialed;
   private int estimated_Page;
   private int actual_Page;
   private int estimated_Actual_Pages;
   private String category;
   private String status;
   private String status_alias;
   
   
   // getter methods for all fields
   public String get_status_alias()
   {
       return status_alias;
   }
    public String get_status()
   {
       return status;
   }
   
   public String get_category()
   {
       return category;
   }
   public String get_project_Id()
   {
       return project_Id;
   }
    public String get_division_Id()
   {
       return division_Id;
   }
     public String get_author_Id()
   {
       return author_Id;
   }
    public String get_color_Id()
   {
       return color_Id;
   }
     public int get_estimated_Page()
   {
       return estimated_Page;
   }
    public int get_actual_Page()
   {
       return actual_Page;
   }
     public int get_estimated_Actual_Pages()
   {
       return estimated_Actual_Pages;
   }
  
   public String get_customer()
   {
       return customer ;
   }
   public String get_division()
   {
       return  division;
   }
   public String get_author()
   {
       return  author;
   }
   public String get_title()
   {
       return  title;
   }
   public String get_planner()
   {
       return  planner;
   }
   public String get_color()
   {
       return  color;
   }
   public String get_trim_Size()
   {
       return  trim_Size;
   }
   public String get_award_Date()
   {
       return  award_Date;
   }
   public String get_due_Date()
   {
       return  due_Date;
   }
   public String get_date_Shipped()
   {
       return  date_Shipped;
   }
   public double get_estiamted_Selling_Value()
   {
       return  estiamted_Selling_Value;
   }
   public String get_partialed()
   {
       return  partialed;
   }
  
   //setter methods for all fields
   
   public void set_status_alias(String status_alias)
   {
       this.status_alias=status_alias;
   }
   
   
   
    public void set_status(String status)
   {
       this.status=status;
   }
   public void set_category(String category)
   {
       this.category=category;
   }
   public void set_project_Id(String project_Id)
   {
       this.project_Id =project_Id ;
   }
   
     public void set_division_Id(String division_Id)
   {
       this.division_Id = division_Id;
   }
      public void set_author_Id(String author_Id)
   {
       this.author_Id =author_Id ;
   }
       public void set_color_Id(String color_Id)
   {
       this.color_Id = color_Id;
   }
        public void set_estimated_Page(int estimated_Page)
   {
       this.estimated_Page = estimated_Page;
   }
         public void set_actual_Page(int actual_Page)
   {
       this.actual_Page = actual_Page;
   }
          public void set_estimated_Actual_Pages(int estimated_Actual_Pages)
   {
       this.estimated_Actual_Pages =estimated_Actual_Pages ;
   }
          
          public void set_customer(String customer)
          {
              this.customer = customer;
          }
            public void set_division(String division )
          {
              this.division = division;
          }
            public void set_author(String author)
          {
              this.author =author ;
          }
            public void set_title(String title)
          {
              this.title = title;
          }
            public void set_planner(String planner)
          {
              this.planner = planner;
          }
            
            
            public void set_color(String color)
          {
              this.color =color ;
          }
            public void set_trim_Size(String trim_Size)
          {
              this.trim_Size = trim_Size;
          }
            public void set_award_Date(String award_Date)
          {
              this.award_Date = award_Date;
          }
            public void set_due_Date(String due_Date)
          {
              this.due_Date = due_Date;
          }
            public void set_date_Shipped(String date_Shipped)
          {
              this.date_Shipped =date_Shipped ;
          }
            public void set_estiamted_Selling_Value(double estiamted_Selling_Value)
          {
              this.estiamted_Selling_Value =estiamted_Selling_Value ;
          }
            public void set_partialed(String partialed)
          {
              this.partialed = partialed;
          }
          
          
    
}
