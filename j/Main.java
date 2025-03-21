import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Recipe class to store details about each recipe
class Recipe {
    private final String name; // Name of the recipe
    private final String ingredients; // Ingredients used in the recipe
    private final String instructions; // Instructions to prepare the recipe
    private final int cookingTime; // Time required to cook the recipe
    private final String imagePath; // Path to the recipe's image
    private final String difficulty; // Difficulty level of the recipe
    private final int prepTime; // Preparation time for the recipe
    private final String cuisineType; // Type of cuisine (e.g., Italian, Indian)
    private final String dietaryPreferences; // Dietary preferences (e.g., Vegan, Gluten-Free)

    // Constructor to initialize the Recipe object
    public Recipe(String name, String ingredients, String instructions, int cookingTime, String imagePath,
                  String difficulty, int prepTime, String cuisineType, String dietaryPreferences) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.imagePath = imagePath;
        this.difficulty = difficulty;
        this.prepTime = prepTime;
        this.cuisineType = cuisineType;
        this.dietaryPreferences = dietaryPreferences;
    }

    // Getter methods for accessing recipe properties
    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public String getInstructions() { return instructions; }
    public int getCookingTime() { return cookingTime; }
    public String getImagePath() { return imagePath; }
    public String getDifficulty() { return difficulty; }
    public int getPrepTime() { return prepTime; }
    public String getCuisineType() { return cuisineType; }
    public String getDietaryPreferences() { return dietaryPreferences; }
}

// GUI class to display and interact with recipes
class RecipeGUI {
    private final List<Recipe> recipes = new ArrayList<>(); // List to store all recipes
    private final JFrame frame = new JFrame("Recipe Library"); // Main window frame
    private final JTextField searchField = new JTextField(20); // Text field for keyword search
    private final JPanel recipePanel = new JPanel(); // Panel to display filtered recipes
    private final JComboBox<String> difficultyFilter = new JComboBox<>(new String[]{"All", "Easy", "Medium", "Hard"}); // Dropdown for difficulty filter
    private final JComboBox<String> cuisineFilter = new JComboBox<>(new String[]{"All", "Italian", "Chinese", "Indian", "Mexican"}); // Dropdown for cuisine filter
    private final JComboBox<String> dietaryFilter = new JComboBox<>(new String[]{"All", "Gluten-Free", "Vegan", "Vegetarian"}); // Dropdown for dietary preferences filter
    public RecipeGUI() {
        initializeRecipes();
        setupGUI();
    }

    private void initializeRecipes() {
        // Recipe 1: Spaghetti Bolognese
        recipes.add(new Recipe("Spaghetti Bolognese",
                "Spaghetti, Ground Beef, Tomato Sauce, Onion, Garlic, Olive Oil, Basil, Oregano, Salt, Pepper",
                "1. Boil spaghetti in salted water until al dente.\n2. In a pan, sauté chopped onions and garlic in olive oil.\n3. Add ground beef to the pan, cooking until browned.\n4. Add tomato sauce, basil, oregano, salt, and pepper. Simmer for 15-20 minutes.\n5. Combine the sauce with the cooked spaghetti and serve with grated Parmesan on top.",
                30, "j/images/shepeggeti.jpg",
                "Medium", 15, "Italian", "Gluten-Free"));

        // Recipe 2: Vegetable Stir Fry
        recipes.add(new Recipe("Vegetable Stir Fry",
                "Broccoli, Bell Peppers, Carrots, Soy Sauce, Garlic, Ginger, Sesame Oil, Rice",
                "1. Cook rice according to package instructions.\n2. In a wok or large pan, heat sesame oil over medium heat.\n3. Add chopped garlic and ginger, sauté for 1 minute.\n4. Add broccoli, bell peppers, and carrots, stir-frying for about 5 minutes.\n5. Add soy sauce and stir to combine. Serve with rice.",
                20, "j/images/vegetable-stir.jpg",
                "Easy", 10, "Chinese", "Vegan"));

        // Recipe 3: Chicken Caesar Salad
        recipes.add(new Recipe("Chicken Caesar Salad",
                "Chicken Breast, Romaine Lettuce, Caesar Dressing, Croutons, Parmesan Cheese",
                "1. Grill or pan-fry chicken breasts until fully cooked, then slice.\n2. Toss chopped romaine lettuce with Caesar dressing.\n3. Add grilled chicken slices, croutons, and grated Parmesan cheese.\n4. Serve immediately.",
                15, "j/images/chicken-ceaser.jpg",
                "Easy", 10, "American", "Gluten-Free"));

        // Recipe 4: Chicken Tikka Masala
        recipes.add(new Recipe("Chicken Tikka Masala",
                "Chicken, Yogurt, Onion, Tomato, Garlic, Ginger, Garam Masala, Cumin, Coriander, Cream, Rice",
                "1. Marinate chicken in yogurt, garlic, ginger, and spices for at least 30 minutes.\n2. Cook marinated chicken in a pan until browned.\n3. Sauté onions, garlic, and ginger in a separate pan, add spices, and cook for a few minutes.\n4. Add tomatoes and simmer until the sauce thickens.\n5. Stir in cream and cooked chicken. Serve with rice.",
                40, "j/images/chicken-tikka-masala.jpg",
                "Hard", 20, "Indian", "Gluten-Free"));

        // Recipe 5: Vegan Chili
        recipes.add(new Recipe("Vegan Chili",
                "Black Beans, Kidney Beans, Tomatoes, Onion, Garlic, Bell Pepper, Chili Powder, Cumin, Olive Oil, Vegetable Broth",
                "1. Heat olive oil in a large pot. Sauté onions, garlic, and bell pepper for 5 minutes.\n2. Add chili powder and cumin, cook for another 2 minutes.\n3. Add beans, tomatoes, and vegetable broth. Bring to a boil.\n4. Reduce heat and simmer for 30 minutes.\n5. Serve hot with cornbread or over rice.",
                40, "j/images/vegan-chilli.jpg",
                "Medium", 15, "Mexican", "Vegan"));

        // Recipe 6: Beef Tacos
        recipes.add(new Recipe("Beef Tacos",
                "Ground Beef, Taco Shells, Lettuce, Tomatoes, Cheddar Cheese, Salsa, Sour Cream",
                "1. Cook ground beef in a skillet over medium heat until browned.\n2. Warm taco shells in the oven.\n3. Assemble tacos by filling shells with cooked beef, shredded lettuce, chopped tomatoes, and grated cheddar cheese.\n4. Top with salsa and sour cream.",
                20, "j/images/beef-tacos.jpg",
                "Easy", 10, "Mexican", "Gluten-Free"));

        // Recipe 7: Shrimp Scampi
        recipes.add(new Recipe("Shrimp Scampi",
                "Shrimp, Garlic, Butter, Olive Oil, Lemon, Parsley, Spaghetti, Parmesan Cheese",
                "1. Cook spaghetti according to package directions.\n2. Sauté garlic in butter and olive oil until fragrant.\n3. Add shrimp and cook until pink and opaque.\n4. Add lemon juice and chopped parsley.\n5. Toss shrimp mixture with cooked spaghetti, then serve with grated Parmesan cheese.",
                25, "j/images/Shrimp-Scampi.jpg",
                "Medium", 15, "Italian", "Gluten-Free"));

        // Recipe 8: Quinoa Salad
        recipes.add(new Recipe("Quinoa Salad",
                "Quinoa, Cucumber, Cherry Tomatoes, Red Onion, Feta Cheese, Olive Oil, Lemon, Parsley",
                "1. Cook quinoa according to package instructions and let it cool.\n2. In a large bowl, combine quinoa, chopped cucumber, halved cherry tomatoes, and finely chopped red onion.\n3. Add crumbled feta cheese, olive oil, lemon juice, and chopped parsley. Toss to combine.\n4. Serve chilled or at room temperature.",
                30, "j/images/quinoa-salad.jpg",
                "Easy", 15, "Mediterranean", "Vegan"));

        // Recipe 9: Margherita Pizza
        recipes.add(new Recipe("Margherita Pizza",
                "Pizza Dough, Tomato Sauce, Mozzarella Cheese, Basil, Olive Oil",
                "1. Preheat the oven to 475°F (245°C).\n2. Roll out pizza dough on a floured surface.\n3. Spread tomato sauce over the dough, leaving a border around the edges.\n4. Top with fresh mozzarella slices and basil leaves.\n5. Bake in the oven for 10-12 minutes or until golden brown. Drizzle with olive oil before serving.",
                25, "j/images/margherita-pizza.jpg",
                "Medium", 15, "Italian", "Gluten-Free"));

        // Recipe 10: Banana Pancakes
        recipes.add(new Recipe("Banana Pancakes",
                "Bananas, Flour, Milk, Eggs, Baking Powder, Vanilla Extract, Butter, Maple Syrup",
                "1. Mash ripe bananas in a bowl.\n2. In a separate bowl, whisk together flour, baking powder, milk, eggs, and vanilla extract.\n3. Add mashed bananas to the wet ingredients and mix to combine.\n4. Heat butter on a griddle or skillet and pour batter to form pancakes.\n5. Cook until golden brown, flipping once. Serve with maple syrup.",
                20, "j/images/banana-pancakes.jpg",
                "Easy", 10, "American", "Vegetarian"));

        // Recipe 11: Vegan Buddha Bowl
        recipes.add(new Recipe("Vegan Buddha Bowl",
                "Quinoa, Chickpeas, Avocado, Spinach, Red Cabbage, Carrot, Sesame Seeds, Olive Oil, Lemon",
                "1. Cook quinoa according to package instructions.\n2. In a pan, sauté chickpeas with olive oil until crispy.\n3. Assemble bowl with quinoa, spinach, shredded cabbage, grated carrot, and avocado slices.\n4. Top with crispy chickpeas, sesame seeds, and a squeeze of lemon juice.",
                35, "j/images/bhudda-bowl.jpg",
                "Medium", 20, "Vegan", "Vegan"));

        // Recipe 12: Pad Thai
        recipes.add(new Recipe("Pad Thai",
                "Rice Noodles, Shrimp, Tofu, Eggs, Bean Sprouts, Peanuts, Lime, Fish Sauce, Soy Sauce, Garlic, Chili Flakes",
                "1. Soak rice noodles according to package instructions.\n2. In a pan, scramble eggs and cook shrimp until pink.\n3. Add tofu and stir-fry for a few minutes.\n4. Toss noodles with fish sauce, soy sauce, chili flakes, and lime juice.\n5. Garnish with peanuts, bean sprouts, and lime wedges.",
                30, "j/images/pad-thai.jpg",
                "Medium", 15, "Thai", "Gluten-Free"));

        // Recipe 13: Grilled Vegetable Skewers
        recipes.add(new Recipe("Grilled Vegetable Skewers",
                "Bell Peppers, Zucchini, Mushrooms, Cherry Tomatoes, Olive Oil, Garlic, Oregano, Lemon",
                "1. Cut vegetables into chunks and thread onto skewers.\n2. Marinate with olive oil, minced garlic, oregano, and lemon juice.\n3. Grill over medium heat until vegetables are tender and slightly charred.\n4. Serve with a side of rice or couscous.",
                25, "j/images/grilled-vegetables.jpg",
                "Easy", 10, "Mediterranean", "Vegetarian"));

        // Recipe 14: Falafel
        recipes.add(new Recipe("Falafel",
                "Chickpeas, Onion, Garlic, Parsley, Cilantro, Cumin, Coriander, Flour, Baking Powder, Olive Oil, Lemon, Tahini",
                "1. Soak chickpeas overnight and blend them with onions, garlic, herbs, and spices.\n2. Form the mixture into balls and refrigerate for 30 minutes.\n3. Deep fry falafel until golden and crispy.\n4. Serve with tahini sauce and pita bread.",
                45, "j/images/falafal.jpg",
                "Hard", 20, "Middle Eastern", "Vegan"));
    }

    private void setupGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the application exits when the window is closed
        frame.setSize(800, 600); // Sets the initial size of the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Enables full-screen mode

        // Top panel to hold the search and filter components
        JPanel topPanel = new JPanel(new BorderLayout());

        // Search label and button components
        JLabel searchLabel = new JLabel("Search:");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchRecipes()); // Action listener for search functionality

        // Panel for search bar and button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchLabel); // Adds the search label to the panel
        searchPanel.add(searchField); // Adds the search text field to the panel
        searchPanel.add(searchButton); // Adds the search button to the panel

        // Panel for filter dropdowns
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Difficulty:")); // Label for difficulty filter
        filterPanel.add(difficultyFilter); // Dropdown for difficulty selection
        filterPanel.add(new JLabel("Cuisine:")); // Label for cuisine filter
        filterPanel.add(cuisineFilter); // Dropdown for cuisine selection
        filterPanel.add(new JLabel("Dietary:")); // Label for dietary preferences filter
        filterPanel.add(dietaryFilter); // Dropdown for dietary preferences selection

        // Add search and filter panels to the top panel
        topPanel.add(searchPanel, BorderLayout.NORTH); // Adds the search panel to the top
        topPanel.add(filterPanel, BorderLayout.SOUTH); // Adds the filter panel below the search panel

        // Recipe panel configuration for displaying the list of recipes
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS)); // Vertical layout for recipe cards
        JScrollPane scrollPane = new JScrollPane(recipePanel); // Adds scroll functionality to the recipe panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Show vertical scrollbar when needed

        // Enhance scroll behavior for smoother navigation
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(12); // Sets the scroll speed

        // Add top panel and scroll pane to the main frame
        frame.add(topPanel, BorderLayout.NORTH); // Places the top panel at the top of the frame
        frame.add(scrollPane, BorderLayout.CENTER); // Places the scroll pane in the center of the frame

        updateRecipePanel(recipes); // Initializes the recipe panel with the full list of recipes
        frame.setVisible(true); // Makes the frame visible
    }

    private void searchRecipes() {
        // Retrieve the search keyword(s) and split by comma, trimming whitespace
        String searchInput = searchField.getText().toLowerCase(); // Converts input to lowercase for case-insensitive search
        String[] keywords = searchInput.split("\\s*,\\s*"); // Splits input into multiple keywords by commas

        // Get the selected filter values for difficulty, cuisine, and dietary preferences
        String selectedDifficulty = (String) difficultyFilter.getSelectedItem(); // Retrieves selected difficulty
        String selectedCuisine = (String) cuisineFilter.getSelectedItem(); // Retrieves selected cuisine
        String selectedDietary = (String) dietaryFilter.getSelectedItem(); // Retrieves selected dietary preference

        // Filter the recipes based on the search keywords and selected filter criteria
        List<Recipe> filteredRecipes = recipes.stream()
                .filter(recipe -> {
                    boolean matchesKeywords = true; // Default to true for keyword matching
                    for (String keyword : keywords) {
                        // Checks if recipe name or ingredients contain the keyword
                        if (!(recipe.getName().toLowerCase().contains(keyword)
                                || recipe.getIngredients().toLowerCase().contains(keyword))) {
                            matchesKeywords = false; // If any keyword doesn't match, set to false
                            break; // Exit the loop if a keyword doesn't match
                        }
                    }
                    // Apply the other filters
                    return matchesKeywords
                            && (selectedDifficulty.equals("All") || recipe.getDifficulty().equals(selectedDifficulty)) // Filter by difficulty
                            && (selectedCuisine.equals("All") || recipe.getCuisineType().equals(selectedCuisine)) // Filter by cuisine
                            && (selectedDietary.equals("All") || recipe.getDietaryPreferences().equals(selectedDietary)); // Filter by dietary preference
                })
                .collect(Collectors.toList()); // Collects the filtered recipes into a list

        updateRecipePanel(filteredRecipes); // Updates the UI to display the filtered recipes
    }

    private void updateRecipePanel(List<Recipe> recipesToShow) {
        recipePanel.removeAll(); // Clears the panel to display new recipes
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS)); // Ensures vertical alignment for cards

        for (Recipe recipe : recipesToShow) {
            // Create a card panel for each recipe
            JPanel card = new JPanel(new BorderLayout());
            card.setPreferredSize(new Dimension(frame.getWidth() - 40, 180)); // Sets preferred size for each card
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180)); // Ensures cards stretch horizontally
            card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Adds a subtle border
            card.setBackground(Color.WHITE); // Sets the card background to white

            // Image section for the recipe card
            JLabel imageLabel = new JLabel();
            loadImage(imageLabel, recipe.getImagePath()); // Loads the image for the recipe
            imageLabel.setPreferredSize(new Dimension(150, 150)); // Sets consistent image size

            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setPreferredSize(new Dimension(160, 160)); // Defines image panel dimensions
            imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around the image
            imagePanel.add(imageLabel, BorderLayout.CENTER); // Places the image at the center

            // Text section for the recipe card
            JLabel nameLabel = new JLabel(recipe.getName(), SwingConstants.LEFT); // Recipe name label
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Sets font for the name label
            nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Adds padding around the name label

            JTextArea ingredientsArea = new JTextArea("Ingredients: " + recipe.getIngredients()); // Displays ingredients
            ingredientsArea.setLineWrap(true); // Enables line wrapping for long text
            ingredientsArea.setWrapStyleWord(true); // Wraps text by word
            ingredientsArea.setEditable(false); // Makes the text area non-editable
            ingredientsArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adds padding for readability
            ingredientsArea.setBackground(new Color(245, 245, 245)); // Light gray background for better visibility

            JButton viewButton = new JButton("View Details"); // Button to view detailed recipe
            viewButton.setFont(new Font("Arial", Font.BOLD, 14)); // Sets font for the button
            viewButton.setForeground(Color.WHITE); // Sets text color to white
            viewButton.setBackground(new Color(255, 80, 80)); // Sets button color to a prominent red
            viewButton.addActionListener(e -> showRecipeDetails(recipe)); // Action listener to show recipe details

            JPanel textPanel = new JPanel(new BorderLayout()); // Text panel for recipe details
            textPanel.add(nameLabel, BorderLayout.NORTH); // Adds the name label to the top
            textPanel.add(ingredientsArea, BorderLayout.CENTER); // Adds the ingredients area to the center
            textPanel.add(viewButton, BorderLayout.SOUTH); // Adds the view button to the bottom

            // Combines image and text sections into the recipe card
            card.add(imagePanel, BorderLayout.WEST); // Places the image section on the left
            card.add(textPanel, BorderLayout.CENTER); // Places the text section in the center

            recipePanel.add(card); // Adds the card to the recipe panel
        }

        recipePanel.revalidate(); // Refreshes the panel to reflect changes
        recipePanel.repaint(); // Repaints the panel to update the UI

        // Automatically scrolls to the top of the recipe panel
        SwingUtilities.invokeLater(() -> {
            // Retrieve the vertical scrollbar of the JScrollPane containing the recipe panel
            JScrollBar verticalBar = ((JScrollPane) recipePanel.getParent().getParent()).getVerticalScrollBar();
            // Set the scrollbar position to the top (minimum value) to ensure the panel starts at the top
            verticalBar.setValue(verticalBar.getMinimum());

        });
    }
    private void loadImage(JLabel label, String imagePath) {
        try {
            // Attempt to load the image from the provided path
            ImageIcon icon = new ImageIcon(imagePath);
            // Scale the image to fit a 150x150 pixel area
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            // Set the scaled image as the icon for the label
            label.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            // Handle cases where the image cannot be loaded
            System.out.println("Error loading image: " + imagePath);
            // Display a fallback message on the label
            label.setText("Image not found");
            // Center-align the text in the label
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    private void showRecipeDetails(Recipe recipe) {
        // Create a new frame to display recipe details
        JFrame detailsFrame = new JFrame(recipe.getName());
        // Set the size of the details window
        detailsFrame.setSize(600, 700);
        // Use a BorderLayout for organizing components
        detailsFrame.setLayout(new BorderLayout());
        // Allow the window to be closed independently of the main application
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Header Panel: Displays the recipe title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(34, 45, 50));  // Set a dark blue-grey background
        JLabel titleLabel = new JLabel(recipe.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));  // Use a bold, modern font
        titleLabel.setForeground(Color.WHITE);  // Set the text color to white
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        detailsFrame.add(headerPanel, BorderLayout.NORTH);  // Add the title panel to the top of the frame

        // Image Panel: Displays the recipe image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 245));  // Light grey background for the image section
        ImageIcon recipeImageIcon = new ImageIcon(recipe.getImagePath());
        JLabel imageLabel = new JLabel();
        // Check if the image exists and scale it to 400x400 pixels
        if (recipeImageIcon != null && recipeImageIcon.getImage() != null) {
            Image img = recipeImageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            recipeImageIcon = new ImageIcon(img);
            imageLabel.setIcon(recipeImageIcon);
        }
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        detailsFrame.add(imagePanel, BorderLayout.CENTER);  // Add the image panel to the center of the frame

        // Details Panel: Displays text details about the recipe
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));  // Arrange components vertically
        detailsPanel.setBackground(Color.WHITE);  // Set a white background

        JTextArea detailsArea = new JTextArea();
        // Populate the text area with recipe details such as ingredients, instructions, and additional info
        detailsArea.setText(
                "Ingredients:\n" + recipe.getIngredients() + "\n\n" +
                        "Instructions:\n" + recipe.getInstructions() + "\n\n" +
                        "Cooking Time: " + recipe.getCookingTime() + " minutes\n" +
                        "Preparation Time: " + recipe.getPrepTime() + " minutes\n" +
                        "Difficulty: " + recipe.getDifficulty() + "\n" +
                        "Cuisine: " + recipe.getCuisineType() + "\n" +
                        "Dietary Preferences: " + recipe.getDietaryPreferences()
        );
        detailsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));  // Use a readable font
        detailsArea.setLineWrap(true);  // Enable line wrapping for long text
        detailsArea.setWrapStyleWord(true);  // Wrap lines at word boundaries
        detailsArea.setEditable(false);  // Make the text area read-only
        detailsArea.setCaretPosition(0);  // Ensure the view starts at the top
        detailsArea.setBackground(Color.WHITE);  // Match the background with the panel

        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  // Add a vertical scrollbar if needed
        detailsPanel.add(detailsScrollPane);  // Add the scroll pane to the details panel

        // Add the details panel to the bottom of the frame
        detailsFrame.add(detailsPanel, BorderLayout.SOUTH);

        // Make the details window visible
        detailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Launch the RecipeGUI application using the SwingUtilities thread
        SwingUtilities.invokeLater(RecipeGUI::new);
    }
}