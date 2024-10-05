const functions = require("firebase-functions");
const admin = require("firebase-admin");
const cors = require("cors")({origin: true});

admin.initializeApp();

exports.addYouthExchangeData = functions.https.onRequest(async (req, res) => {
  cors(req, res, async () => {
    if (req.method !== "POST") {
      return res.status(405).json({error: "Method Not Allowed"});
    }
    try {
      const data = req.body;
      if (!data.id || !data.name || !data.age) {
        return res.status(400).json({
          error: "Bad Request",
          message: "Missing required fields (id, name, age)",
        });
      }
      const collection = admin.firestore().collection("Players");
      const docRef = collection.doc(data.id);
      const doc = await docRef.get();
      if (doc.exists) {
        return res.status(409).json({
          error: "Conflict",
          message: "Data already exists for this player.",
        });
      }
      await docRef.set(data);
      return res
        .status(201)
        .json({message: "Data received and stored successfully."});
    } catch (error) {
      console.error("Error storing data:", error.stack || error.message);
      return res.status(500).json({
        error: "Internal Server Error",
        message: error.message,
      });
    }
  });
});
