import useSWR from "swr"

const fetcher = async url => {
    const res = await fetch(url)

    if (!res.ok) {
        const error = new Error('An error occurred while fetching the data.')
        // Attach extra info to the error object.
        error.info = await res.json()
        error.status = res.status
        throw error
    }

    return res.json()
}

const baseUrl = "http://localhost:8080"

export const useGetPosts = path => {
    if (!path) {
        throw new Error("Path is required")
    }

    const url = baseUrl + path

    const { data: posts, error } = useSWR(url, fetcher)

    let parsed;
    try {
        parsed = JSON.parse(posts);
        console.log('JSON array parsed successfully');
        console.log(parsed)
    } catch (err) {
        console.log('Invalid JSON provided');
    }

    return { parsed, error }
}