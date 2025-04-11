package com.gitrank.client;

import com.gitrank.exception.GitHubScrapingException;
import com.gitrank.model.GitHubApiRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class GitHubScrapingClient implements GithubClient {
    
    private static final String GITHUB_SEARCH_URL = "https://github.com/search";
    
    @Override
    public List<GitHubApiRepository> searchRepositories(String query, List<String> languages, 
                                                   List<String> topics, int page, int size) {
        try {
            String url = buildSearchUrl(query, languages, topics, page);
            Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();
            
            return parseRepositoryElements(doc.select("div.Box-row"), size);
        } catch (Exception e) {
            throw new GitHubScrapingException("Failed to scrape GitHub repositories", e);
        }
    }
    
    private String buildSearchUrl(String query, List<String> languages, 
                                List<String> topics, int page) {
        StringBuilder url = new StringBuilder(GITHUB_SEARCH_URL)
            .append("?q=").append(query)
            .append("&type=repositories")
            .append("&p=").append(page);
            
        if (languages != null && !languages.isEmpty()) {
            url.append("&l=").append(String.join("&l=", languages));
        }
        
        return url.toString();
    }
    
    private List<GitHubApiRepository> parseRepositoryElements(Elements elements, int size) {
        List<GitHubApiRepository> repositories = new ArrayList<>();
        
        for (Element element : elements) {
            if (repositories.size() >= size) break;
            
            try {
                GitHubApiRepository repo = new GitHubApiRepository();
                repo.setName(extractName(element));
                repo.setFullName(extractFullName(element));
                repo.setDescription(extractDescription(element));
                repo.setStargazersCount(extractStars(element));
                repo.setForksCount(extractForks(element));
                repo.setLanguage(extractLanguage(element));
                
                repositories.add(repo);
            } catch (Exception e) {
                // Log parsing error and continue with next repository
                System.err.println("Error parsing repository element: " + e.getMessage());
            }
        }
        
        return repositories;
    }
    
    // Helper methods for extracting data from HTML elements
    private String extractName(Element element) {
        return element.select("a.v-align-middle").text();
    }
    
    private String extractFullName(Element element) {
        return element.select("a.v-align-middle").attr("href").substring(1);
    }
    
    private String extractDescription(Element element) {
        return element.select("p.mb-1").text();
    }
    
    private int extractStars(Element element) {
        return Integer.parseInt(element.select("a[href$=stargazers]").text().replace(",", ""));
    }
    
    private int extractForks(Element element) {
        return Integer.parseInt(element.select("a[href$=network]").text().replace(",", ""));
    }
    
    private String extractLanguage(Element element) {
        return element.select("span[itemprop=programmingLanguage]").text();
    }
} 