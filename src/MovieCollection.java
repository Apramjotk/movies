
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }


        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }



    private void searchCast() {
        ArrayList<String> newList = new ArrayList<String>();
        System.out.println("Enter a keyword search term here:");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();
        ArrayList<String> cast = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            String[] cast1 = movies.get(i).getCast().split("\\|");

            for (int b = 0; b < cast1.length; b++) {

                if (cast1[b].toLowerCase().indexOf(searchTerm) != -1) {
                    cast.add(cast1[b]);
                }

            }

            for(String name : cast){
                if(!newList.contains(name)){
                    newList.add(name);
                }
            }

        }
        Collections.sort(newList);
        for (int i = 0; i < newList.size(); i++)
        {
            String title =  newList.get(i);
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which actor would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        int Check= 0;
        System.out.println("You chose actor:"+ newList.get(choice-1));
        ArrayList<Movie> movie= new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {

            if (movies.get(i).getCast().contains(newList.get(choice-1))){
                movie.add(movies.get(i));
            }

        }
        int add=0;

        for (int i = 0; i <movie.size(); i++) {
            add++;
            System.out.println(""+ add+ ". "+ movie.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int ken = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movie.get(ken - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();


        }














    private void searchKeywords()
    {
        System.out.println("Enter a keyword search term here:");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();
        ArrayList<Movie> searchWord = new ArrayList<Movie>();
        for (int i =0; i<movies.size(); i++){
            if (movies.get(i).getKeywords().toLowerCase().indexOf(searchTerm)!=-1){
                searchWord.add(movies.get(i));
            }
        }
        sortResults(searchWord);
        for (int i = 0; i < searchWord.size(); i++)
        {
            String title =  searchWord.get(i).getKeywords();
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = searchWord.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<String> cast = new ArrayList<String>();
        String s="";

        for (int i = 0; i < movies.size(); i++) {
          String[] cast1 = movies.get(i).getGenres().split("\\|");

            for (int b = 0; b < cast1.length; b++) {
                    cast.add(cast1[b]);

            }

            for(String name : cast){
                if(!newList.contains(name)){
                    newList.add(name);
                }
            }


        }

        int Check= 0;
        ArrayList<Movie> movie= new ArrayList<Movie>();
        Collections.sort(newList);
        for (int b = 0; b < newList.size(); b++) {
            Check++;
            System.out.println(""+ Check+ ". "+newList.get(b));
        }
            System.out.println("Which genre would you like to learn more about?");
            System.out.print("Enter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

        for (int i = 0; i < movies.size(); i++) {

            if (movies.get(i).getGenres().contains(newList.get(choice-1))){
                movie.add(movies.get(i));
            }

        }
        int add=0;

        for (int i = 0; i <movie.size(); i++) {
        add++;
            System.out.println(""+ add+ ". "+ movie.get(i).getTitle());
            }
        System.out.println("Which movie would you like to learn more about? ");
        System.out.print("Enter number: ");

        int ken = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movie.get(ken - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

            }











    private void listHighestRated() {
        ArrayList<Double> rate = new ArrayList<Double>();
        ArrayList<String> name = new ArrayList<String>();
        Double[] dblArray = new Double[rate.size()];
        String[] movie = new String[name.size()];
        for (int i = 0; i < movies.size(); i++) {
            rate.add(movies.get(i).getUserRating());
            name.add(movies.get(i).getTitle());
        }


                dblArray = rate.toArray(dblArray);
                movie = name.toArray(movie);

        ArrayList<Movie> spread = new ArrayList<Movie>();
        for (int k = 0; k < movies.size(); k++) {

            if (movies.get(k).getUserRating() == (dblArray[k])) {
                spread.add(movies.get(k));


            }

        }


        int n = dblArray.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (spread.get(j).getUserRating() < spread.get(min_idx).getUserRating())
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Movie temp =spread.get(min_idx);
            spread.set(min_idx,spread.get(i));
            spread.set(i,temp);


        }
        ArrayList<Movie> best = new ArrayList<Movie>();
        for (int i = spread.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            best.add( spread.get(i));
        }


        ArrayList<String> order = new ArrayList<String>();

        Movie sem= movies.get(0);;


        int add=0;
       // System.out.println(spread);


        for (int i = 0; i <50; i++) {
            add++;

            System.out.println(""+ add+ "  Title  "+ best.get(i).getTitle()+ "\nUser Rating: "+ best.get(i).getUserRating());

        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int ken = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = best.get(ken - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();









        }

    private void listHighestRevenue()
    {
        ArrayList<Integer> rate = new ArrayList<Integer>();
        ArrayList<String> name = new ArrayList<String>();
        Integer[] dblArray = new Integer[rate.size()];
        String[] movie = new String[name.size()];
        for (int i = 0; i < movies.size(); i++) {
            rate.add(movies.get(i).getRevenue());
            name.add(movies.get(i).getTitle());
        }


        dblArray = rate.toArray(dblArray);
        movie = name.toArray(movie);

        ArrayList<Movie> spread = new ArrayList<Movie>();
        for (int k = 0; k < movies.size(); k++) {

            if (movies.get(k).getRevenue() == (dblArray[k])) {
                spread.add(movies.get(k));


            }

        }


        int n = dblArray.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (spread.get(j).getRevenue() < spread.get(min_idx).getRevenue())
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Movie temp =spread.get(min_idx);
            spread.set(min_idx,spread.get(i));
            spread.set(i,temp);


        }
        ArrayList<Movie> best = new ArrayList<Movie>();
        for (int i = spread.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            best.add( spread.get(i));
        }


        ArrayList<String> order = new ArrayList<String>();

        Movie sem= movies.get(0);;


        int add=0;
        // System.out.println(spread);


        for (int i = 0; i <50; i++) {
            add++;

            System.out.println(""+ add+ "  Title  "+ best.get(i).getTitle()+ "\nRevenue:  "+ best.get(i).getRevenue());

        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int ken = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = best.get(ken - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();








    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}