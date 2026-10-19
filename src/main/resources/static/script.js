document.getElementById('resumeForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    // Get form values
    const candidateName = document.getElementById('candidateName').value.trim();
    const resumeText = document.getElementById('resumeText').value.trim();
    
    // UI Elements
    const analyzeBtn = document.getElementById('analyzeBtn');
    const loadingDiv = document.getElementById('loading');
    const resultSection = document.getElementById('resultSection');
    
    // Reset UI state
    analyzeBtn.disabled = true;
    loadingDiv.classList.remove('hidden');
    resultSection.classList.add('hidden');
    
    try {
        // We use relative path because the HTML is served from the same Spring Boot server
        const response = await fetch(`/api/resume/analyze?candidateName=${encodeURIComponent(candidateName)}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain' 
            },
            body: resumeText
        });
        
        if (!response.ok) {
            if (response.status === 401) {
                throw new Error("Unauthorized: Please disable Spring Security or configure credentials.");
            }
            throw new Error(`Server returned status: ${response.status}`);
        }
        
        const data = await response.json();
        
        // Populate results
        document.getElementById('matchScore').textContent = data.matchScore;
        document.getElementById('aiFeedback').textContent = data.aiFeedback;
        
        // Show results
        resultSection.classList.remove('hidden');
        
    } catch (error) {
        alert("Error analyzing resume: " + error.message);
        console.error(error);
    } finally {
        // Restore button and hide loading text
        analyzeBtn.disabled = false;
        loadingDiv.classList.add('hidden');
    }
});