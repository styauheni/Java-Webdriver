@predefined
Feature: Smoke steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    When I click on element using JavaScript with xpath "(//input[@name='btnK'])[1]"
    Then I wait for element with xpath "//*[@id='res']" to be present
    Then element with xpath "//*[@id='res']" should contain text "Cucumber"

  @predefined2
  Scenario: Predefined steps for Bing
    Given I open url "https://bing.com"
    Then I should see page title as "Bing"
    Then element with xpath "//*[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='q']"
    When I click on element using JavaScript with xpath "//label[@for='sb_form_go']"
    Then I wait for element with xpath "//*[@id='b_results']" to be present
    Then element with xpath "//*[@id='b_results']" should contain text "Cucumber"

  @predefined3
  Scenario: Predefined steps for Duckduckgo
    Given I open url "https://duckduckgo.com/"
    Then I should see page title as "DuckDuckGo — Privacy, simplified."
    Then element with xpath "//*[@name='q']" should be present
    When I type "<Behavior Driven Development>" into element with xpath "//*[@name='q']"
    When I click on element using JavaScript with xpath "//*[@id='search_button_homepage']"
    Then I wait for element with xpath "//*[@id='links']" to be present
    Then element with xpath "//*[@id='links']" should contain text "Cucumber"

  @predefined4
  Scenario: Predefined steps for Yahoo
    Given I open url "https://www.yahoo.com/"
    Then I should see page title as "Yahoo"
    Then element with xpath "//*[@name='p']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='p']"
    When I click on element using JavaScript with xpath "//*[contains(@id,'search-button')]"
    Then I wait for element with xpath "//*[@id='results']" to be present
    Then element with xpath "//*[@id='results']" should contain text "Cucumber"

  @predefined5
  Scenario: Predefined steps for Gibiru
    Given I open url "https://gibiru.com/"
    Then I should see page title as "Gibiru – Protecting your privacy since 2009"
    Then element with xpath "//*[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='q']"
    When I click on element using JavaScript with xpath "//*[@id='button-addon2']"
    Then I wait for element with xpath "//div[@class='gsc-results gsc-webResult']" to be present
    Then element with xpath "//div[@class='gsc-results gsc-webResult']" should contain text "Cucumber"

  @predefined6
  Scenario: Predefined steps for Swisscows
    Given I open url "https://swisscows.com/"
    Then I should see page title as "Swisscows the alternative, data secure search engine."
    Then element with xpath "//*[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='query']"
    When I click on element using JavaScript with xpath "//*[@class='search-submit']"
    Then I wait for element with xpath "//*[@class='web-results']" to be present
    Then I wait for 2 sec
    Then element with xpath "//*[@class='web-results']" should contain text "Cucumber"

  @predefined7
  Scenario: Predefined steps for Search Encrypt
    Given I open url "https://www.searchencrypt.com/"
    Then I should see page title as "Search Encrypt - The Privacy Based Search Engine"
    Then element with xpath "(//*[@name='q'])[1]" should be present
    When I type "Behavior Driven Development" into element with xpath "(//*[@name='q'])[1]"
    When I click on element using JavaScript with xpath "(//*[@id='btn-secure-search'])[1]"
    Then I wait for element with xpath "//div[@id='container-wrapper']" to be present
    Then element with xpath "//div[@id='container-wrapper']" should contain text "Cucumber"

  @predefined8
  Scenario: Predefined Steps for Startpage
    Given I open url "https://www.startpage.com/"
    Then I should see page title as "Startpage.com - The world's most private search engine"
    Then element with xpath "//*[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='query']"
    When I click on element using JavaScript with xpath "//*[@class='search-form__button']"
    Then I wait for element with xpath "//div[@class='layout-web__body']" to be present
    Then I wait for 2 sec
    Then element with xpath "//div[@class='layout-web__body']" should contain text "Cucumber"

  @predefined9
  Scenario: Predefined steps for Yandex
    Given I open url "https://yandex.com/"
    Then I should see page title as "Yandex"
    Then element with xpath "//*[@name='text']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='text']"
    When I click on element using JavaScript with xpath "//div[@class='search2__button']/button"
    Then I wait for element with xpath "//*[@class='content__left']" to be present
    Then element with xpath "//*[@class='content__left']" should contain text "Cucumber"

  @predefined10
  Scenario: Predefined steps for Boardreader
    Given I open url "http://boardreader.com/"
    Then I should see page title as "Boardreader - Forum Search Engine"
    Then element with xpath "//*[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@id='title-query']"
    Then I wait for element with xpath "//ul[@class='mdl-list']" to be present
    Then I wait for 2 sec
    Then element with xpath "//ul[@class='mdl-list']" should contain text "Cucumber"

  @predefined11
  Scenario: Predefined steps for Ecosia
    Given I open url "https://www.ecosia.org/"
    Then I should see page title as "Ecosia - the search engine that plants trees"
    Then element with xpath "//*[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@name='q']"
    When I click on element using JavaScript with xpath "//*[@aria-label='Submit']"
    Then I wait for element with xpath "//*[@class='container results']" to be present
    Then element with xpath "//*[@class='container results']" should contain text "Cucumber"

  @predefined12
  Scenario: Predefined steps for PCS online
    Given I open url "https://portnov.net/"
    Then I should see page title contains "Testing"
    When I mouse over element with xpath "//div[@class='header_nav']//a[text()='Courses']"
    When I click on element using JavaScript with xpath "//div[@class='header_nav']//a[contains(@href,'automation')]"
    Then I wait for element with xpath "//div[@class='col-md-8']" to be present
    Then element with xpath "//h1[@class='entry-title']" should contain text "Automation"

  @predefined13
  Scenario: predefined steps for PCS 
    Given I open url "https://www.portnov.com/"
    Then I should see page title contains "QA"
    Then element with xpath "//input[@name='searchword']" should be present
    When I type "automation bootcamp" into element with xpath "//input[@name='searchword']"
    And I click on element using JavaScript with xpath "//input[@name='searchword']/../button"
    Then I wait for element with xpath "//div[@class='search-results']" to be present
    And element with xpath "//div[@class='search-results']" should contain text "Bootcamp"
    

    
    


  
  
    
    
    
    
