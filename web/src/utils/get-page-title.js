import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Dev Cloud Admin'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
