
# Orbit: A Valentine's Experience

## Deployment Guide

### 1. GitHub Pages (The Game)
1.  Go to your GitHub Repository > **Settings**.
2.  Navigate to **Pages**.
3.  Under **Build and deployment**, select **Source** as `Deploy from a branch`.
4.  Select the `main` branch and the `/ (root)` folder.
5.  Click **Save**.
6.  Wait a few minutes. Your game will be live at `https://<your-username>.github.io/<repo-name>/`.

### 2. Cloudflare Worker (The Room Generator)
*Optional: The game currently generates room codes locally using PeerJS, so this worker is just a nice-to-have "API" if you want to expand later. The current `index.html` uses PeerJS directly.*

1.  Go to the Cloudflare Dashboard > **Workers & Pages**.
2.  Click **Create Application** > **Create Worker**.
3.  Name it `orbit-game`.
4.  Click **Deploy**.
5.  Click **Edit Code**.
6.  Copy the content of `worker.js` from this repo and paste it into the online editor.
7.  Click **Save and Deploy**.
8.  (Optional) Copy the Worker URL and update `WORKER_API_URL` in `index.html` if you want to use the API for room generation in the future.

## How to Play

1.  **You (Host):** Open the website. Click **"Create Room"**.
2.  **Share:** Copy the Room ID or the Link and send it to her.
3.  **Her (Join):** She opens the link. The game will auto-connect.
4.  **Controls:** Drag your finger on the screen (or use mouse) to fly the ship.
    *   **Goal:** Avoid the red boxes.
    *   **Teamwork:** You see each other's ships. Try to fly in formation!

## Troubleshooting
*   **"Connection Error"**: Ensure you are both on HTTPS (GitHub Pages provides this automatically). WebRTC requires HTTPS.
*   **"Disconnected"**: Refresh both pages and try a new Room ID.
