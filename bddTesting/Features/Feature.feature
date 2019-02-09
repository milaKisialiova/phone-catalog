Feature: Web Catalog

  Scenario Outline: Filter phones
    Given Open the Firefox and Web Catalog
    When Filter by country "<country_filter>"
    And Filter by state "<state_filter>"
    Then See row with "<code>" "<phone>" "<country>" "<state>"

    Examples: 
      | country_filter | state_filter | code  | phone       | country  | state     |
      | Ethiopia       | Any          | (251) | 914701723   | Ethiopia | Not valid |
      | Any            | Not valid    | (212) | 6617344445  | Morocco  | Not valid |
      | Morocco        | Valid        | (212) | 698054317   | Morocco  | Valid     |

