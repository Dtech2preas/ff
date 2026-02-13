
export default {
  async fetch(request) {
    // 1. Handle CORS (Allow all)
    if (request.method === "OPTIONS") {
      return new Response(null, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, POST, OPTIONS",
          "Access-Control-Allow-Headers": "Content-Type",
        },
      });
    }

    const url = new URL(request.url);

    // 2. Generate a Unique Room Code (Simple Random)
    if (url.pathname === "/api/create-room") {
      const roomCode = Math.random().toString(36).substring(2, 8).toUpperCase();
      return new Response(JSON.stringify({ room: roomCode }), {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      });
    }

    // 3. Health Check
    return new Response("Orbit Game API is Ready. Use /api/create-room to start.", {
      headers: { "Content-Type": "text/plain", "Access-Control-Allow-Origin": "*" },
    });
  },
};
